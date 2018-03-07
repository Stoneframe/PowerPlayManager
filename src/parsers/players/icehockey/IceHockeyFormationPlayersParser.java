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

public class IceHockeyFormationPlayersParser
	extends PlayersParser<IceHockeyAttributes>
{
	@Override
	public String getName()
	{
		return "Formation";
	}

	@Override
	public List<Player<IceHockeyAttributes>> parsePlayers(String textToParse) throws ParseException
	{
		try
		{
			List<Player<IceHockeyAttributes>> players = new LinkedList<>();

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
			e.printStackTrace();
			throw new ParseException(e);
		}
	}

	private static IceHockeyPlayer parsePlayer(String textToParse)
	{
		String[] columns = textToParse.split("\t");

		return new IceHockeyPlayer(
				parseName(columns[1]),
				parseAge(columns[2]),
				0,
				parseSide(columns[3]),
				parseAttributes(Arrays.copyOfRange(columns, 4, 12)),
				0);
	}

	private static String parseName(String textToParse)
	{
		String[] split = textToParse.trim().split(" ");

		return String.format("%s %s", split[1], split[2]);
	}

	private static int parseAge(String textToParse)
	{
		return Integer.parseInt(textToParse.trim());
	}

	private static Side parseSide(String textToParse)
	{
		if (textToParse.equals("U"))
		{
			return Side.UNIVERSAL;
		}
		else if (textToParse.equals("L"))
		{
			return Side.LEFT;
		}
		else if (textToParse.equals("R"))
		{
			return Side.RIGHT;
		}
		else
		{
			return Side.UNKNOWN;
		}
	}

	private static IceHockeyAttributes parseAttributes(String[] textsToParse)
	{
		IceHockeyAttributes attributes = new IceHockeyAttributes();

		attributes.setGoa(Integer.parseInt(textsToParse[0].trim()));
		attributes.setDef(Integer.parseInt(textsToParse[1].trim()));
		attributes.setOff(Integer.parseInt(textsToParse[2].trim()));
		attributes.setSho(Integer.parseInt(textsToParse[3].trim()));
		attributes.setPas(Integer.parseInt(textsToParse[4].trim()));
		attributes.setTec(Integer.parseInt(textsToParse[5].trim()));
		attributes.setAgr(Integer.parseInt(textsToParse[6].trim()));

		return attributes;
	}
}
