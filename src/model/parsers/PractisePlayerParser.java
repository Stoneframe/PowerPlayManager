package model.parsers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import model.Attributes;
import model.Player;
import model.PlayerParser;
import model.Rooster;
import model.Side;

public class PractisePlayerParser implements PlayerParser
{
	@Override
	public List<Player> parsePlayers(String textToParse)
	{
		String[] lines = textToParse.replace("\n", "\t").split("\t");

		List<Player> players = new LinkedList<Player>();

		for (int i = 0; i < lines.length; i += 23)
		{
			Player player = new Player(
					parseName(lines[i]),
					Side.UNKNOWN,
					parseAttributes(Arrays.copyOfRange(lines, i + 5, i + 20)));

			players.add(player);
		}

		return players;
	}

	@Override
	public Rooster parseRooster(String textToParse)
	{
		String[] lines = textToParse.replace("\n", "\t").split("\t");

		Rooster rooster = new Rooster();

		for (int i = 0; i < lines.length; i += 23)
		{
			Player player = new Player(
					parseName(lines[i]),
					Side.UNKNOWN,
					parseAttributes(Arrays.copyOfRange(lines, i + 5, i + 20)));

			rooster.add(player);
		}

		return rooster;
	}

	private static String parseName(String text)
	{
		String[] split = text.split(" ");

		return String.format("%s %s", split[1], split[2]);
	}

	private static Attributes parseAttributes(String[] texts)
	{
		Attributes attributes = new Attributes();

		attributes.setGoa(Integer.parseInt(texts[0].substring(0, 2)));
		attributes.setQGoa(Integer.parseInt(texts[0].substring(2, 4)));

		attributes.setFip(Integer.parseInt(texts[2].substring(0, 2)));
		attributes.setQFip(Integer.parseInt(texts[2].substring(2, 4)));

		attributes.setSho(Integer.parseInt(texts[4].substring(0, 2)));
		attributes.setQSho(Integer.parseInt(texts[4].substring(2, 4)));

		attributes.setBlk(Integer.parseInt(texts[6].substring(0, 2)));
		attributes.setQBlk(Integer.parseInt(texts[6].substring(2, 4)));

		attributes.setPas(Integer.parseInt(texts[8].substring(0, 2)));
		attributes.setQPas(Integer.parseInt(texts[8].substring(2, 4)));

		attributes.setTec(Integer.parseInt(texts[10].substring(0, 2)));
		attributes.setQTec(Integer.parseInt(texts[10].substring(2, 4)));

		attributes.setSpe(Integer.parseInt(texts[12].substring(0, 2)));
		attributes.setQSpe(Integer.parseInt(texts[12].substring(2, 4)));

		attributes.setAgr(Integer.parseInt(texts[14].substring(0, 2)));
		attributes.setQAgr(Integer.parseInt(texts[14].substring(2, 4)));

		return attributes;
	}
}
