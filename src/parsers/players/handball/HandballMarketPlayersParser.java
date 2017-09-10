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

public class HandballMarketPlayersParser extends PlayersParser<HandballAttributes>
{
	@Override
	public String getName()
	{
		return "Market";
	}

	@Override
	public List<Player<HandballAttributes>> parsePlayers(String textToParse) throws ParseException
	{
		try
		{
			List<Player<HandballAttributes>> players =
					new LinkedList<Player<HandballAttributes>>();

			String[] lines = textToParse.split("\n");

			for (int i = 0; i < lines.length; i += 4)
			{
				HandballPlayer player = new HandballPlayer(
						parseName(lines[i]),
						parseAge(lines[i + 3].split("\t")[0]),
						parseCL(lines[i + 3].split("\t")[3]),
						parseSide(lines[i + 3].split("\t")[14]),
						parseAttributes(
							Arrays.copyOfRange(lines[i + 3].trim().split("\t"), 4, 12)),
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

	private static HandballAttributes parseAttributes(String[] texts)
	{
		HandballAttributes attributes = new HandballAttributes();

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
		int rating = Integer.parseInt(text.substring(0, text.length() - 2));
		int quality = Integer.parseInt(text.substring(text.length() - 2, text.length()));

		return new int[]
		{
				rating,
				quality
		};
	}
}
