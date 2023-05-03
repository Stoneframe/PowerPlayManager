package parsers.players;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import model.Attributes;
import model.Countries;
import model.Player;
import model.Side;
import parsers.ParseException;
import parsers.SideParser;

public abstract class RegexPlayersParser<A extends Attributes>
	extends PlayersParser<A>
{
	private final int numberOfLines;
	private final Pattern regexPattern;

	protected final boolean includeCL;
	protected final boolean includeSide;
	protected final boolean includeQualities;
	protected final boolean includeExperience;
	protected final boolean includeChemistry;
	protected final boolean includeEnergy;
	protected final boolean includeTraining;

	protected RegexPlayersParser(
		int numberOfLines,
		Pattern regexPattern,
		boolean includeCL,
		boolean includeSide,
		boolean includeQualities,
		boolean includeExperience,
		boolean includeChemistry,
		boolean includeEnergy,
		boolean includeTraining)
	{
		this.numberOfLines = numberOfLines;
		this.regexPattern = regexPattern;

		this.includeCL = includeCL;
		this.includeSide = includeSide;
		this.includeQualities = includeQualities;
		this.includeExperience = includeExperience;
		this.includeChemistry = includeChemistry;
		this.includeEnergy = includeEnergy;
		this.includeTraining = includeTraining;
	}

	@Override
	public abstract String getName();

	@Override
	public List<Player<A>> parsePlayers(String textToParse)
			throws ParseException
	{
		List<Player<A>> players = new LinkedList<>();

		for (String line : toSinglePlayerPerLine(textToParse))
		{
			Matcher matcher = regexPattern.matcher(line);

			if (matcher.find())
			{
				Player<A> player = createPlayer(matcher);

				players.add(player);
			}
			else
			{
				throw new ParseException();
			}
		}

		return players;
	}

	private Player<A> createPlayer(Matcher matcher)
	{
		A attributes = createAttributes(matcher, includeQualities);
		
		return new Player<A>(
			matcher.group("name"),
			matcher.group("country"),
			Integer.parseInt(matcher.group("age")),
			includeCL ? Integer.parseInt(matcher.group("cl")) : -1,
			includeSide ? SideParser.parseSide(matcher.group("side")) : Side.UNKNOWN,
			attributes,
			includeExperience ? Integer.parseInt(matcher.group("experience")) : 0,
			includeChemistry ? Integer.parseInt(matcher.group("chemistry")) : 0,
			includeEnergy ? Integer.parseInt(matcher.group("energy")) : 100,
			includeTraining ? Double.parseDouble(matcher.group("training")) : 0);
	}

	protected List<String> toSinglePlayerPerLine(String textToParse)
	{
		List<String> lines = new LinkedList<>();

		String[] split = textToParse.split("\n");

		for (int i = 0; i < split.length; i += numberOfLines)
		{
			lines.add(String.join("\t", Arrays.copyOfRange(split, i, i + numberOfLines)));
		}

		return lines;
	}

	protected abstract A createAttributes(Matcher matcher, boolean includeQuality);

	protected static Pattern createPattern(String... regexes)
	{
		return Pattern.compile("^" + String.join("\t", regexes));
	}

	protected static String ignore()
	{
		return "[^\\t]*";
	}

	protected static String name()
	{
		return "(?<country>("
			+ String.join("|", Countries.LIST).replace("-", "\\-")
			+ ")+) (?<name>\\S+(((?!( Dagar| Spelare| \\d))( \\S+))+)).*";
	}

	protected static String age()
	{
		return "[ ]*(?<age>\\d+)[ ]*";
	}

	protected static String cl()
	{
		return "[ ]*(?<cl>\\d)\\/6[ ]*";
	}

	protected static String attributes(String... names)
	{
		return String.join(
			"\t",
			Arrays.stream(names).map(n -> attribute(n)).collect(Collectors.toList()));
	}

	protected static String attribute(String name)
	{
		return String.format("[ ]*((?<%s>\\d+))[ ]*", name, name);
	}

	protected static String attributesWithQualities(String... names)
	{
		return String.join(
			"\t",
			Arrays.stream(names).map(n -> attributeWithQuality(n)).collect(Collectors.toList()));
	}

	protected static String attributeWithQuality(String name)
	{
		return String.format("[ ]*((?<%s>\\d+)(?<q%s>(\\d{2})))[ ]*", name, name);
	}

	protected static String experience()
	{
		return "[ ]*(?<experience>\\d+)[ ]*";
	}

	protected static String energy()
	{
		return "[ ]*(\\d{1,3}\\/)?(?<energy>\\d{1,3})[ ]*";
	}

	protected static String chemistry()
	{
		return "[ ]*(?<chemistry>\\d+)[ ]*";
	}

	protected static String side()
	{
		return "[ ]*(?<side>(U|L|R))[ ]*";
	}

	protected static String training()
	{
		return "(?<training>\\d+\\.\\d{2})";
	}
}
