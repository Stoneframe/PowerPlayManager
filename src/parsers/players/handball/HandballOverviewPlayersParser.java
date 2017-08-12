package parsers.players.handball;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import model.Player;
import model.Side;
import model.handball.HandballAttributes;
import model.handball.HandballPlayer;
import parsers.ParseException;
import parsers.players.PlayersParser;

public class HandballOverviewPlayersParser
		implements PlayersParser<HandballAttributes>
{
	@Override
	public List<Player<HandballAttributes>> parsePlayers(String textToParse)
			throws ParseException
	{
		try
		{
			String[] lines = textToParse.split("\n");

			List<Player<HandballAttributes>> players =
					new LinkedList<Player<HandballAttributes>>();

			for (int i = 0; i < lines.length; i++)
			{
				String[] columns = lines[i].split("\t");

				HandballPlayer player = new HandballPlayer(
						parseName(columns[0]),
						parseSide(columns[16]),
						parseAttributes(Arrays.copyOfRange(columns, 6, 14)));

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
		String[] split = text.split(" ");

		return String.format("%s %s", split[1], split[2]);
	}

	private static Side parseSide(String text)
	{
		text = text.trim();

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

	private static HandballAttributes parseAttributes(String[] texts)
	{
		HandballAttributes attributes = new HandballAttributes();

		attributes.setGoa(Integer.parseInt(texts[0].trim()));
		attributes.setFip(Integer.parseInt(texts[1].trim()));
		attributes.setSho(Integer.parseInt(texts[2].trim()));
		attributes.setBlk(Integer.parseInt(texts[3].trim()));
		attributes.setPas(Integer.parseInt(texts[4].trim()));
		attributes.setTec(Integer.parseInt(texts[5].trim()));
		attributes.setSpe(Integer.parseInt(texts[6].trim()));
		attributes.setAgr(Integer.parseInt(texts[7].trim()));

		return attributes;
	}
	
	@Override
	public String toString()
	{
		return "Overview";
	}
}
