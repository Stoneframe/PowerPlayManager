package parsers.players.icehockey;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import model.Player;
import model.Side;
import model.icehockey.IceHockeyAttributes;
import model.icehockey.IceHockeyPlayer;
import parsers.ParseException;
import parsers.players.PlayersParser;

public class IceHockeyOverviewPlayersParser
	extends PlayersParser<IceHockeyAttributes>
{
	@Override
	public String getName()
	{
		return "Overview";
	}

	@Override
	public List<Player<IceHockeyAttributes>> parsePlayers(String textToParse) throws ParseException
	{
		try
		{
			List<Player<IceHockeyAttributes>> players =
					new LinkedList<Player<IceHockeyAttributes>>();

			String[] lines = textToParse.split("\n");

			for (int i = 0; i < lines.length; i++)
			{
				IceHockeyPlayer player = parsePlayer(lines[i]);

				players.add(player);
			}

			return players;
		}
		catch (Exception e)
		{
			throw new ParseException(e);
		}
	}

	private static IceHockeyPlayer parsePlayer(String text)
	{
		String[] columns = text.split("\t");

		return new IceHockeyPlayer(
				parseName(columns[0]),
				parseAge(columns[2]),
				parseCL(columns[5]),
				parseSide(columns[15]),
				parseAttributes(Arrays.copyOfRange(columns, 6, 14)),
				0);
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

	private static IceHockeyAttributes parseAttributes(String[] texts)
	{
		IceHockeyAttributes attributes = new IceHockeyAttributes();

		attributes.setGoa(Integer.parseInt(texts[0].trim()));
		attributes.setDef(Integer.parseInt(texts[1].trim()));
		attributes.setOff(Integer.parseInt(texts[2].trim()));
		attributes.setSho(Integer.parseInt(texts[3].trim()));
		attributes.setPas(Integer.parseInt(texts[4].trim()));
		attributes.setTec(Integer.parseInt(texts[5].trim()));
		attributes.setAgr(Integer.parseInt(texts[6].trim()));

		return attributes;
	}
}
