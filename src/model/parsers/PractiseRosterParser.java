package model.parsers;

import java.util.Arrays;

import model.Attributes;
import model.Player;
import model.RosterParser;
import model.Roster;
import model.Side;

public class PractiseRosterParser implements RosterParser
{
	private static final int FIELDS_PER_PLAYER = 15;
	private static final int ATTRIBUTE_FIELDS_PER_PLAYER = 8;
	private static final int ATTRIBUTES_START_FIELD = 5;

	@Override
	public Roster parseRoster(String textToParse)
	{
		String[] lines = textToParse.replace("\n", "\t").split("\t");

		Roster roster = new Roster();

		for (int i = 0; i < lines.length; i += FIELDS_PER_PLAYER)
		{
			Player player = new Player(
					parseName(lines[i]),
					Side.UNKNOWN,
					parseAttributes(Arrays
							.copyOfRange(
								lines,
								i + ATTRIBUTES_START_FIELD,
								i + ATTRIBUTES_START_FIELD + ATTRIBUTE_FIELDS_PER_PLAYER)));

			roster.add(player);
		}

		return roster;
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

		attributes.setFip(Integer.parseInt(texts[1].substring(0, 2)));
		attributes.setQFip(Integer.parseInt(texts[1].substring(2, 4)));

		attributes.setSho(Integer.parseInt(texts[2].substring(0, 2)));
		attributes.setQSho(Integer.parseInt(texts[2].substring(2, 4)));

		attributes.setBlk(Integer.parseInt(texts[3].substring(0, 2)));
		attributes.setQBlk(Integer.parseInt(texts[3].substring(2, 4)));

		attributes.setPas(Integer.parseInt(texts[4].substring(0, 2)));
		attributes.setQPas(Integer.parseInt(texts[4].substring(2, 4)));

		attributes.setTec(Integer.parseInt(texts[5].substring(0, 2)));
		attributes.setQTec(Integer.parseInt(texts[5].substring(2, 4)));

		attributes.setSpe(Integer.parseInt(texts[6].substring(0, 2)));
		attributes.setQSpe(Integer.parseInt(texts[6].substring(2, 4)));

		attributes.setAgr(Integer.parseInt(texts[7].substring(0, 2)));
		attributes.setQAgr(Integer.parseInt(texts[7].substring(2, 4)));

		return attributes;
	}
}
