package parsers.players.icehockey;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import model.Player;
import model.icehockey.IceHockeyAttributes;
import model.icehockey.IceHockeyPlayer;
import parsers.ParseException;
import parsers.SideParser;
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
			throw new ParseException(e);
		}
	}

	private static IceHockeyPlayer parsePlayer(String textToParse)
	{
		String[] columns = textToParse.split("\t");

		return new IceHockeyPlayer(
				parseName(columns[1].trim()),
				parseAge(columns[2].trim()),
				0,
				SideParser.parseSide(columns[3].trim()),
				parseAttributes(Arrays.copyOfRange(columns, 4, 12)),
				0);
	}

	private static String parseName(String textToParse)
	{
		String[] split = textToParse.split(" ");

		return String.format("%s %s", split[1], split[2]);
	}

	private static int parseAge(String textToParse)
	{
		return Integer.parseInt(textToParse.trim());
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
