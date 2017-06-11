package model.parsers;

import java.util.Arrays;

import model.Attributes;
import model.Player;
import model.RoosterParser;
import model.Rooster;
import model.Side;

public class OverviewRoosterParser implements RoosterParser
{
	@Override
	public Rooster parseRooster(String textToParse)
	{
		String[] lines = textToParse.split("\n");

		Rooster rooster = new Rooster();

		for (int i = 0; i < lines.length; i++)
		{
			String[] columns = lines[i].split("\t");

			Player player = new Player(
					parseName(columns[0]),
					parseSide(columns[16]),
					parseAttributes(Arrays.copyOfRange(columns, 6, 14)));

			rooster.add(player);
		}

		return rooster;
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

	private static Attributes parseAttributes(String[] texts)
	{
		Attributes attributes = new Attributes();

		attributes.setGoa(Integer.parseInt(texts[0]));
		attributes.setFip(Integer.parseInt(texts[1]));
		attributes.setSho(Integer.parseInt(texts[2]));
		attributes.setBlk(Integer.parseInt(texts[3]));
		attributes.setPas(Integer.parseInt(texts[4]));
		attributes.setTec(Integer.parseInt(texts[5]));
		attributes.setSpe(Integer.parseInt(texts[6]));
		attributes.setAgr(Integer.parseInt(texts[7]));

		return attributes;
	}
}
