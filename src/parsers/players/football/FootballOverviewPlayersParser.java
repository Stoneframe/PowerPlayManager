package parsers.players.football;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import model.Player;
import model.football.FootballAttributes;
import model.football.FootballPlayer;
import parsers.ParseException;
import parsers.SideParser;
import parsers.players.PlayersParser;

public class FootballOverviewPlayersParser
	extends PlayersParser<FootballAttributes>
{
	@Override
	public String getName()
	{
		return "Overview";
	}

	@Override
	public List<Player<FootballAttributes>> parsePlayers(String textToParse) throws ParseException
	{
		try
		{
			String[] lines = textToParse.split("\n");

			List<Player<FootballAttributes>> players = new LinkedList<>();

			for (int i = 0; i < lines.length; i++)
			{
				String[] columns = lines[i].split("\t");

				FootballPlayer player = new FootballPlayer(
						parseName(columns[0]),
						parseAge(columns[2].trim()),
						parseCL(columns[5]),
						SideParser.parseSide(columns[17]),
						parseAttributes(Arrays.copyOfRange(columns, 6, 15)),
						0,
						0,
						0,
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
		String[] split = text.split(" ");

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

	private static FootballAttributes parseAttributes(String[] texts)
	{
		FootballAttributes attributes = new FootballAttributes();

		attributes.setGoa(Integer.parseInt(texts[0].trim()));
		attributes.setDef(Integer.parseInt(texts[1].trim()));
		attributes.setMid(Integer.parseInt(texts[2].trim()));
		attributes.setOff(Integer.parseInt(texts[3].trim()));
		attributes.setSho(Integer.parseInt(texts[4].trim()));
		attributes.setPas(Integer.parseInt(texts[5].trim()));
		attributes.setTec(Integer.parseInt(texts[6].trim()));
		attributes.setSpe(Integer.parseInt(texts[7].trim()));
		attributes.setHea(Integer.parseInt(texts[8].trim()));

		return attributes;
	}
}
