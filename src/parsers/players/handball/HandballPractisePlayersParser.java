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

public class HandballPractisePlayersParser
	extends PlayersParser<HandballAttributes>
{
	private static final int FIELDS_PER_PLAYER = 15;
	private static final int AGE_FIELD = 2;
	private static final int CL_FIELD = 4;
	private static final int ATTRIBUTE_FIELDS_PER_PLAYER = 8;
	private static final int ATTRIBUTES_START_FIELD = 5;
	private static final int TRAINING_FIELD = 13;

	@Override
	public String getName()
	{
		return "Practise";
	}

	@Override
	public List<Player<HandballAttributes>> parsePlayers(String textToParse)
			throws ParseException
	{
		try
		{
			String[] lines = textToParse.replace("\n", "\t").split("\t");

			List<Player<HandballAttributes>> players = new LinkedList<Player<HandballAttributes>>();

			for (int i = 0; i < lines.length; i += FIELDS_PER_PLAYER)
			{
				HandballPlayer player = new HandballPlayer(
						parseName(lines[i]),
						parseAge(lines[i + AGE_FIELD].trim()),
						parseCL(lines[i + CL_FIELD].trim()),
						Side.UNKNOWN,
						parseAttributes(
							Arrays.copyOfRange(
								lines,
								i + ATTRIBUTES_START_FIELD,
								i + ATTRIBUTES_START_FIELD + ATTRIBUTE_FIELDS_PER_PLAYER)),
						parseTraining(lines[i + TRAINING_FIELD]));

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

		int[] agr = parseAttribute(texts[7].trim());
		attributes.setAgr(agr[0]);
		attributes.setQAgr(agr[1]);

		return attributes;
	}

	private static int[] parseAttribute(String text)
	{
		int rating = Integer.parseInt(text.substring(0, text.length() - 2));

		int quality = Integer
				.parseInt(text.substring(text.length() - 2, text.length()));

		return new int[]
		{
				rating,
				quality
		};
	}

	private static double parseTraining(String text)
	{
		return Double.parseDouble(text);
	}
}
