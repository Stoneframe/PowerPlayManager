package parsers.players.football;

import java.util.Arrays;
import java.util.List;

import model.Player;
import model.Side;
import model.football.FootballAttributes;
import model.football.FootballPlayer;
import parsers.ParseException;
import parsers.SideParser;
import parsers.players.PlayersParser;

public class FootballProfilePlayersParser
	extends PlayersParser<FootballAttributes>
{
	@Override
	public String getName()
	{
		return "Profile";
	}

	@Override
	public List<Player<FootballAttributes>> parsePlayers(String textToParse) throws ParseException
	{
		try
		{
			FootballPlayer player = new FootballPlayer(
				parseName(textToParse),
				parseAge(textToParse),
				parseCL(textToParse),
				parseSide(textToParse),
				parseAttributes(textToParse),
				parseExperience(textToParse),
				parseChemistry(textToParse),
				parseEnergy(textToParse),
				parseTraining(textToParse));

			return Arrays.asList(player);
		}
		catch (Exception e)
		{
			throw new ParseException(e);
		}
	}

	private static String parseName(String text)
	{
		String[] split = text.split("\n")[0].split("  ")[1].split(" ");

		return String.format("%s %s", split[split.length - 2], split[split.length - 1]);
	}

	private static int parseAge(String text)
	{
		String ageText = Arrays
			.stream(text.split("\n"))
			.filter(s -> s.contains("Ålder"))
			.findFirst()
			.get();

		String[] ageTextElements = ageText.split("\t");
		return Integer.parseInt(ageTextElements[ageTextElements.length - 1]);
	}

	private static int parseCL(String text)
	{
		String clText = Arrays
			.stream(text.split("\n"))
			.filter(s -> s.contains("KL"))
			.findFirst()
			.get();

		String clText1 = clText.split("/")[0];
		String cl = clText1.substring(clText1.length() - 1);

		return Integer.parseUnsignedInt(cl);
	}

	private static Side parseSide(String text)
	{
		String sideText = Arrays
			.stream(text.split("\n"))
			.filter(s -> s.contains("FvS"))
			.findFirst()
			.get();

		String[] sideTextElements = sideText.split("\t");
		String side = sideTextElements[sideTextElements.length - 1];

		return SideParser.parseSide(side);
	}

	private static FootballAttributes parseAttributes(String text)
	{
		FootballAttributes attributes = new FootballAttributes();

		int[] goa = parseAttribute(text, "Mål");
		attributes.setGoa(goa[0]);
		attributes.setQGoa(goa[1]);

		int[] def = parseAttribute(text, "För");
		attributes.setDef(def[0]);
		attributes.setQDef(def[1]);

		int[] mid = parseAttribute(text, "Mit");
		attributes.setMid(mid[0]);
		attributes.setQMid(mid[1]);

		int[] off = parseAttribute(text, "Off");
		attributes.setOff(off[0]);
		attributes.setQOff(off[1]);

		int[] sho = parseAttribute(text, "Sko");
		attributes.setSho(sho[0]);
		attributes.setQSho(sho[1]);

		int[] pas = parseAttribute(text, "Pas");
		attributes.setPas(pas[0]);
		attributes.setQPas(pas[1]);

		int[] tec = parseAttribute(text, "Tek");
		attributes.setTec(tec[0]);
		attributes.setQTec(tec[1]);

		int[] spe = parseAttribute(text, "Sna");
		attributes.setSpe(spe[0]);
		attributes.setQSpe(spe[1]);

		int[] hea = parseAttribute(text, "Nic");
		attributes.setHea(hea[0]);
		attributes.setQHea(hea[1]);

		return attributes;
	}

	private static int[] parseAttribute(String text, String attrName)
	{
		String[] split = Arrays
			.stream(text.split("\n"))
			.filter(s -> s.startsWith(attrName))
			.findFirst()
			.get()
			.split("\t");

		int rating = Integer.parseInt(split[1]);
		int quality = Integer.parseInt(split[split.length - 1]);

		return new int[]
		{
				rating,
				quality,
		};
	}

	private static int parseExperience(String text)
	{
		String expText = Arrays
			.stream(text.split("\n"))
			.filter(s -> s.contains("Erf"))
			.findFirst()
			.get();

		return Integer.parseInt(expText.split("\t")[1]);
	}

	private static int parseChemistry(String text)
	{
		String chemText = Arrays
			.stream(text.split("\n"))
			.filter(s -> s.contains("Kem"))
			.findFirst()
			.get();

		return Integer.parseInt(chemText.split("\t")[1].replace("%", "").replace("-", "0"));
	}

	private static int parseEnergy(String text)
	{
		String eneText = Arrays
			.stream(text.split("\n"))
			.filter(s -> s.contains("Ene"))
			.findFirst()
			.get();

		return Integer.parseInt(eneText.split("\t")[1].split("/")[1]);
	}

	private static double parseTraining(String text)
	{
		return 0;
	}
}
