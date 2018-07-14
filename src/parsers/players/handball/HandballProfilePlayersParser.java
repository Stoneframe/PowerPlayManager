package parsers.players.handball;

import java.util.Arrays;
import java.util.List;

import model.Player;
import model.Side;
import model.handball.HandballAttributes;
import model.handball.HandballPlayer;
import parsers.ParseException;
import parsers.SideParser;
import parsers.players.PlayersParser;

public class HandballProfilePlayersParser
	extends PlayersParser<HandballAttributes>
{
	@Override
	public String getName()
	{
		return "Profile";
	}

	@Override
	public List<Player<HandballAttributes>> parsePlayers(String textToParse) throws ParseException
	{
		try
		{
			HandballPlayer player = new HandballPlayer(
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

	private static HandballAttributes parseAttributes(String text)
	{
		HandballAttributes attributes = new HandballAttributes();

		int[] goa = parseAttribute(text, "Mål");
		attributes.setGoa(goa[0]);
		attributes.setQGoa(goa[1]);

		int[] fip = parseAttribute(text, "Uts");
		attributes.setFip(fip[0]);
		attributes.setQFip(fip[1]);

		int[] sho = parseAttribute(text, "Sko");
		attributes.setSho(sho[0]);
		attributes.setQSho(sho[1]);

		int[] blk = parseAttribute(text, "Blk");
		attributes.setBlk(blk[0]);
		attributes.setQBlk(blk[1]);

		int[] pas = parseAttribute(text, "Pas");
		attributes.setPas(pas[0]);
		attributes.setQPas(pas[1]);

		int[] tec = parseAttribute(text, "Tek");
		attributes.setTec(tec[0]);
		attributes.setQTec(tec[1]);

		int[] spe = parseAttribute(text, "Sna");
		attributes.setSpe(spe[0]);
		attributes.setQSpe(spe[1]);

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
