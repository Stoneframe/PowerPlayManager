package importer;

import java.io.Closeable;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.http.auth.InvalidCredentialsException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

import files.FileHandler;
import model.Attributes;
import model.Player;
import model.Roster;

public abstract class Importer<A extends Attributes>
	implements
		Closeable
{
	public static final int TEAM = 0;
	public static final int MARKET = 1;

	private final WebClient client;

	private final FileHandler<A> fileHandler;

	private final LineUpParser<A> lineUpParser;
	private final TrainingParser<A> trainingParser;
	private final MarketParser<A> marketParser;

	protected Importer(
		FileHandler<A> fileHandler,
		LineUpParser<A> lineUpParser,
		TrainingParser<A> trainingParser,
		MarketParser<A> marketParser)
	{
		this.fileHandler = fileHandler;

		this.lineUpParser = lineUpParser;
		this.trainingParser = trainingParser;
		this.marketParser = marketParser;

		client = getWebClient();
	}

	public List<Player<A>> importPlayers(int type, String username, String password)
			throws InvalidCredentialsException, IOException
	{
		switch (type)
		{
			case TEAM:
				return importTeam(username, password);

			case MARKET:
				return importMarket(username, password);

			default:
				throw new IllegalArgumentException("Import type not supported: " + type);
		}
	}

	private List<Player<A>> importTeam(String username, String password)
			throws IOException, InvalidCredentialsException
	{
		Roster<A> roster = new Roster<>();

		Path path = getCachePath("Team");

		if (isCacheUpToDate(path))
		{
			importPlayersFromCache(roster, path);
		}
		else
		{
			importTeamFromPpm(roster, username, password);
			exportPlayersToCache(roster, path);
		}

		return roster.stream().collect(Collectors.toList());
	}

	private List<Player<A>> importMarket(String username, String password)
			throws IOException, InvalidCredentialsException
	{
		Roster<A> roster = new Roster<>();

		Path path = getCachePath("Market");

		if (isCacheUpToDate(path))
		{
			importPlayersFromCache(roster, path);
		}
		else
		{
			importMarketFromPpm(roster, username, password);
			exportPlayersToCache(roster, path);
		}

		return roster.stream().collect(Collectors.toList());
	}

	private Path getCachePath(String context)
	{
		return Paths.get(
			System.getenv("APPDATA"),
			"PPM Assistant",
			"Cache",
			getSport() + " " + context);
	}

	private boolean isCacheUpToDate(Path path)
	{
		LocalDateTime modifiedTime = fileHandler.getFileModifiedDate(path);

		LocalDateTime latestRefresh = getLatestRefresh();

		return modifiedTime.isAfter(latestRefresh);
	}

	private LocalDateTime getLatestRefresh()
	{
		if (LocalTime.now().isAfter(LocalTime.of(6, 0)))
		{
			return LocalDate.now().atTime(6, 0);
		}
		else
		{
			return LocalDate.now().minusDays(1).atTime(6, 0);
		}
	}

	private void importPlayersFromCache(Roster<A> roster, Path path)
	{
		fileHandler.loadPlayersFromFile(path.toFile(), roster);
	}

	private void exportPlayersToCache(Roster<A> roster, Path path)
	{
		fileHandler.savePlayersToFile(path.toFile(), roster);
	}

	private void importTeamFromPpm(Roster<A> roster, String username, String password)
			throws IOException, InvalidCredentialsException
	{
		login(username, password);

		importPlayers(roster, lineUpParser);
		importPlayers(roster, trainingParser);

		logout();
	}

	private void importMarketFromPpm(Roster<A> roster, String username, String password)
			throws IOException, InvalidCredentialsException
	{
		login(username, password);

		HtmlPage page = client.getPage(marketParser.getAddress());

		HtmlSelect typeSelect = page.getElementByName("market_type");

		typeSelect.setSelectedIndex(1);

		HtmlButton searchButton = page.getFirstByXPath(
			"//*[@id=\"filter_market\"]/form/table[5]/tbody/tr/td[1]/button");

		HtmlPage searchPage = searchButton.click();

		int pageIndex = 1;

		while (true)
		{
			Document document = Jsoup.parse(searchPage.asXml());

			// Document document = Jsoup.parse(TestData.get());
			List<Player<A>> players = marketParser.parse(document);

			if (players.isEmpty())
			{
				break;
			}

			roster.addAll(players);

			pageIndex++;

			HtmlAnchor next = searchPage.getFirstByXPath(
				"//div[contains(@class, 'pagination')]/ul/li/a[contains(text(), '"
					+ pageIndex
					+ "')]");

			searchPage = next.click();
		}

		logout();
	}

	@Override
	public void close()
	{
		client.close();
	}

	private WebClient getWebClient()
	{
		WebClient client = new WebClient(BrowserVersion.EDGE);

		client.getOptions().setTimeout(60000);
		client.getOptions().setRedirectEnabled(true);
		client.getOptions().setJavaScriptEnabled(true);
		client.getOptions().setThrowExceptionOnFailingStatusCode(false);
		client.getOptions().setThrowExceptionOnScriptError(false);
		client.getOptions().setCssEnabled(false);
		client.getOptions().setUseInsecureSSL(true);

		return client;
	}

	private void login(String username, String password)
			throws IOException, InvalidCredentialsException
	{
		HtmlPage loginPage = client.getPage("https://ppm.powerplaymanager.com/sv/");

		HtmlForm loginForm = loginPage.getForms().get(0);

		HtmlTextInput usernameInput = loginForm.getInputByName("username");
		HtmlPasswordInput passwordInput = loginForm.getInputByName("password");

		HtmlSubmitInput submitInput = loginForm.getInputByValue("OK");

		usernameInput.type(username);
		passwordInput.type(password);

		HtmlPage page = submitInput.click(false, false, false, false, false, true, false);

		if (page.asXml().contains("Fel lösenord")
			|| page.asXml().contains("Inloggnings information saknas"))
		{
			throw new InvalidCredentialsException();
		}
	}

	private void logout() throws IOException
	{
		client.getPage("https://www.powerplaymanager.com/action/action_ams_user_logout.php?lng=sv");
	}

	private void importPlayers(Roster<A> roster, Parser<A> parser) throws IOException
	{
		HtmlPage page = client.getPage(parser.getAddress());

		Document document = Jsoup.parse(page.asXml());

		List<Player<A>> players = parser.parse(document);

		roster.addAll(players);
	}

	protected abstract String getSport();
}
