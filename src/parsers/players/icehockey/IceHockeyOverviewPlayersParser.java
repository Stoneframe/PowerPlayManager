package parsers.players.icehockey;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import model.Player;
import model.icehockey.IceHockeyAttributes;
import model.icehockey.IceHockeyPlayer;
import parsers.ParseException;
import parsers.SideParser;
import parsers.players.AbstractPlayersParser;

public class IceHockeyOverviewPlayersParser
	extends AbstractPlayersParser<IceHockeyAttributes>
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
				SideParser.parseSide(columns[15]),
				parseAttributes(Arrays.copyOfRange(columns, 6, 14)),
				0,
				0,
				0,
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
