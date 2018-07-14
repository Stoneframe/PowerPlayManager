package parsers.players.icehockey;

import java.util.Arrays;
import java.util.List;

import model.Player;
import model.Side;
import model.icehockey.IceHockeyAttributes;
import model.icehockey.IceHockeyPlayer;
import parsers.ParseException;
import parsers.SideParser;
import parsers.players.PlayersParser;

public class IceHockeyProfilePlayersParser
	extends PlayersParser<IceHockeyAttributes>
{
	@Override
	public String getName()
	{
		return "Profile";
	}

	@Override
	public List<Player<IceHockeyAttributes>> parsePlayers(String textToParse) throws ParseException
	{
		try
		{
			IceHockeyPlayer player = new IceHockeyPlayer(
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
			e.printStackTrace();
			throw new ParseException(e);
		}
	}

	private static String parseName(String text)
	{
		String[] split = text.split("\n")[0].split(",")[0].split(" ");

		return String.format("%s %s", split[split.length - 2], split[split.length - 1]);
	}

	private static int parseAge(String text)
	{
		String ageText = Arrays
			.stream(text.split("\n"))
			.filter(s -> s.startsWith("Ålder"))
			.findFirst()
			.get();

		return Integer.parseInt(ageText.split("\t")[1]);
	}

	private static int parseCL(String text)
	{
		String clText = Arrays
			.stream(text.split("\n"))
			.filter(s -> s.startsWith("KL"))
			.findFirst()
			.get();

		String cl = clText
			.replace("(", "")
			.replace(")", "")
			.split("\t")[1].split(" ")[1].split("/")[0];

		return Integer.parseInt(cl);
	}

	private static Side parseSide(String text)
	{
		String side = Arrays
			.stream(text.split("\n"))
			.filter(s -> s.startsWith("FvS"))
			.findFirst()
			.get()
			.split("\t")[1];

		return SideParser.parseSide(side);
	}

	private static IceHockeyAttributes parseAttributes(String text)
	{
		IceHockeyAttributes attributes = new IceHockeyAttributes();

		int[] goa = parseAttribute(text, "Mål");
		attributes.setGoa(goa[0]);
		attributes.setQGoa(goa[1]);

		int[] def = parseAttribute(text, "För");
		attributes.setDef(def[0]);
		attributes.setQDef(def[1]);

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

		int[] agr = parseAttribute(text, "Agr");
		attributes.setAgr(agr[0]);
		attributes.setQAgr(agr[1]);

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
