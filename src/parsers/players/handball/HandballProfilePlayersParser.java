package parsers.players.handball;

import java.util.Arrays;
import java.util.List;

import model.Player;
import model.Side;
import model.handball.HandballAttributes;
import model.handball.HandballPlayer;
import parsers.ParseException;
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
				.filter(s -> s.startsWith("�lder"))
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

		if (side.equals("Universal"))
		{
			return Side.UNIVERSAL;
		}
		else if (side.equals("V�nster"))
		{
			return Side.LEFT;
		}
		else if (side.equals("H�ger"))
		{
			return Side.RIGHT;
		}
		else
		{
			return Side.UNKNOWN;
		}
	}

	private static HandballAttributes parseAttributes(String text)
	{
		HandballAttributes attributes = new HandballAttributes();

		int[] goa = parseAttribute(text, "M�l");
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

	private static double parseTraining(String text)
	{
		return 0;
	}
}
