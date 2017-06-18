package model.parsers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import model.Attributes;
import model.Player;
import model.PlayersParser;
import model.Side;

public class MarketPlayersParser implements PlayersParser
{
	@Override
	public List<Player> parseRoster(String textToParse) throws ParseException
	{
		List<Player> players = new LinkedList<Player>();

		String[] lines = textToParse.split("\n");

		for (int i = 0; i < lines.length; i += 4)
		{
			Player player = new Player(
					parseName(lines[i]),
					parseSide(lines[i + 3].split("\t")[14]),
					parseAttributes(
						Arrays.copyOfRange(
							lines[i + 3].trim().split("\t"),
							4,
							12)));

			players.add(player);
		}

		return players;
	}

	private static String parseName(String text)
	{
		String[] split = text.trim().split(" ");

		return String.format("%s %s", split[1], split[2]);
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

	private static Attributes parseAttributes(String[] texts)
	{
		Attributes attributes = new Attributes();

		int[] goa = parseAttribute(texts[0]);
		attributes.setGoa(goa[0]);
		attributes.setQGoa(goa[1]);

		int[] fip = parseAttribute(texts[1]);
		attributes.setFip(fip[0]);
		attributes.setQFip(fip[1]);

		int[] sho = parseAttribute(texts[2]);
		attributes.setSho(sho[0]);
		attributes.setQSho(sho[1]);

		int[] blk = parseAttribute(texts[3]);
		attributes.setBlk(blk[0]);
		attributes.setQBlk(blk[1]);

		int[] pas = parseAttribute(texts[4]);
		attributes.setPas(pas[0]);
		attributes.setQPas(pas[1]);

		int[] tec = parseAttribute(texts[5]);
		attributes.setTec(tec[0]);
		attributes.setQTec(tec[1]);

		int[] spe = parseAttribute(texts[6]);
		attributes.setSpe(spe[0]);
		attributes.setQSpe(spe[1]);

		int[] agr = parseAttribute(texts[7]);
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
