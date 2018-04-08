package parsers.players.football;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import model.Player;
import model.Side;
import model.football.FootballAttributes;
import model.football.FootballPlayer;
import parsers.ParseException;
import parsers.players.PlayersParser;

public class FootballMarketPlayersParser
	extends PlayersParser<FootballAttributes>
{

	@Override
	public String getName()
	{
		return "Market";
	}

	@Override
	public List<Player<FootballAttributes>> parsePlayers(String textToParse) throws ParseException
	{
		try
		{
			List<Player<FootballAttributes>> players = new LinkedList<Player<FootballAttributes>>();

			String[] lines = textToParse.split("\n");

			for (int i = 0; i < lines.length; i += 4)
			{
				FootballPlayer player = new FootballPlayer(
						parseName(lines[i]),
						parseAge(lines[i + 3].trim().split("\t")[0].trim()),
						parseCL(lines[i + 3].trim().split("\t")[2]),
						parseSide(lines[i + 3].trim().split("\t")[14]),
						parseAttributes(Arrays.copyOfRange(lines[i + 3].trim().split("\t"), 3, 12)),
						0);

				players.add(player);
			}

			return players;
		}
		catch (Exception e)
		{
			throw new ParseException(e);
		}
	}

	private static String parseName(String text)
	{
		String[] split = text.trim().split(" ");

		return String.format("%s %s", split[1], split[2]);
	}

	private static int parseAge(String text)
	{
		return Integer.parseInt(text);
	}

	private static int parseCL(String text)
	{
		return Integer.parseInt(text.split("/")[0]);
	}

	private static Side parseSide(String text)
	{
		if (text.equals("U"))
		{
			return Side.UNIVERSAL;
		}
		else if (text.equals("L"))
		{
			return Side.LEFT;
		}
		else if (text.equals("R"))
		{
			return Side.RIGHT;
		}
		else
		{
			return Side.UNKNOWN;
		}
	}

	private static FootballAttributes parseAttributes(String[] texts)
	{
		FootballAttributes attributes = new FootballAttributes();

		int[] goa = parseAttribute(texts[0].trim());
		attributes.setGoa(goa[0]);
		attributes.setQGoa(goa[1]);

		int[] def = parseAttribute(texts[1].trim());
		attributes.setDef(def[0]);
		attributes.setQDef(def[1]);

		int[] mid = parseAttribute(texts[2].trim());
		attributes.setMid(mid[0]);
		attributes.setQMid(mid[1]);

		int[] off = parseAttribute(texts[3].trim());
		attributes.setOff(off[0]);
		attributes.setQOff(off[1]);

		int[] sho = parseAttribute(texts[4].trim());
		attributes.setSho(sho[0]);
		attributes.setQSho(sho[1]);

		int[] pas = parseAttribute(texts[5].trim());
		attributes.setPas(pas[0]);
		attributes.setQPas(pas[1]);

		int[] tec = parseAttribute(texts[6].trim());
		attributes.setTec(tec[0]);
		attributes.setQTec(tec[1]);

		int[] spe = parseAttribute(texts[7].trim());
		attributes.setSpe(spe[0]);
		attributes.setQSpe(spe[1]);

		int[] hea = parseAttribute(texts[8].trim());
		attributes.setHea(hea[0]);
		attributes.setQHea(hea[1]);

		return attributes;
	}

	private static int[] parseAttribute(String text)
	{
		int rating = Integer.parseInt(text.substring(0, text.length() - 2));
		int quality = Integer.parseInt(text.substring(text.length() - 2, text.length()));

		return new int[]
		{
				rating,
				quality
		};
	}
}
