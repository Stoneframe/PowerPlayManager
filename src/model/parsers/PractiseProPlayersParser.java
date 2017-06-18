package model.parsers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import model.Attributes;
import model.Player;
import model.PlayersParser;
import model.Side;

public class PractiseProPlayersParser implements PlayersParser
{
	private static final int FIELDS_PER_PLAYER = 23;
	private static final int ATTRIBUTE_FIELDS_PER_PLAYER = 15;
	private static final int ATTRIBUTES_START_FIELD = 5;

	@Override
	public List<Player> parseRoster(String textToParse) throws ParseException
	{
		try
		{
			String[] lines = textToParse.replace("\n", "\t").split("\t");

			List<Player> players = new LinkedList<Player>();

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

	private static Attributes parseAttributes(String[] texts)
	{
		Attributes attributes = new Attributes();

		int[] goa = parseAttribute(texts[0]);
		attributes.setGoa(goa[0]);
		attributes.setQGoa(goa[1]);

		int[] fip = parseAttribute(texts[2]);
		attributes.setFip(fip[0]);
		attributes.setQFip(fip[1]);

		int[] sho = parseAttribute(texts[4]);
		attributes.setSho(sho[0]);
		attributes.setQSho(sho[1]);

		int[] blk = parseAttribute(texts[6]);
		attributes.setBlk(blk[0]);
		attributes.setQBlk(blk[1]);

		int[] pas = parseAttribute(texts[8]);
		attributes.setPas(pas[0]);
		attributes.setQPas(pas[1]);

		int[] tec = parseAttribute(texts[10]);
		attributes.setTec(tec[0]);
		attributes.setQTec(tec[1]);

		int[] spe = parseAttribute(texts[12]);
		attributes.setSpe(spe[0]);
		attributes.setQSpe(spe[1]);

		int[] agr = parseAttribute(texts[14]);
		attributes.setAgr(agr[0]);
		attributes.setQAgr(agr[1]);

		return attributes;
	}

	private static int[] parseAttribute(String text)
	{
		int rating = Integer.parseInt(
			text.substring(
				0,
				text.length() - 2));

		int quality = Integer.parseInt(
			text.substring(
				text.length() - 2,
				text.length()));

		return new int[]
		{
				rating,
				quality
		};
	}
}
