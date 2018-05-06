package parsers.players.icehockey;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import model.Player;
import model.Side;
import model.icehockey.IceHockeyAttributes;
import model.icehockey.IceHockeyPlayer;
import parsers.ParseException;
import parsers.SideParser;
import parsers.players.PlayersParser;

public class IceHockeyMarketPlayersParser
	extends PlayersParser<IceHockeyAttributes>
{
	@Override
	public String getName()
	{
		return "Market";
	}

	@Override
	public List<Player<IceHockeyAttributes>> parsePlayers(String textToParse) throws ParseException
	{
		try
		{
			List<Player<IceHockeyAttributes>> players =
					new LinkedList<Player<IceHockeyAttributes>>();

			String[] lines = textToParse.split("\n");

			for (int i = 0; i < lines.length; i += 4)
			{
				IceHockeyPlayer player = parsePlayer(Arrays.copyOfRange(lines, i, i + 4));

				players.add(player);
			}

			return players;
		}
		catch (Exception e)
		{
			throw new ParseException(e);
		}
	}

	private static IceHockeyPlayer parsePlayer(String[] lines)
	{
		return new IceHockeyPlayer(
				parseName(lines[0]),
				parseAge(lines[3].split("\t")[0]),
				parseCL(lines[3].split("\t")[3]),
				parseSide(lines[3]),
				parseAttributes(lines[3]),
				0);
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
		text = text.split("\t")[13];

		return SideParser.parseSide(text);
	}

	private static IceHockeyAttributes parseAttributes(String text)
	{
		String[] texts = Arrays.copyOfRange(text.split("\t"), 4, 11);

		IceHockeyAttributes attributes = new IceHockeyAttributes();

		int[] goa = parseAttribute(texts[0]);
		attributes.setGoa(goa[0]);
		attributes.setQGoa(goa[1]);

		int[] def = parseAttribute(texts[1]);
		attributes.setDef(def[0]);
		attributes.setQDef(def[1]);

		int[] off = parseAttribute(texts[2]);
		attributes.setOff(off[0]);
		attributes.setQOff(off[1]);

		int[] sho = parseAttribute(texts[3]);
		attributes.setSho(sho[0]);
		attributes.setQSho(sho[1]);

		int[] pas = parseAttribute(texts[4]);
		attributes.setPas(pas[0]);
		attributes.setQPas(pas[1]);

		int[] tec = parseAttribute(texts[5]);
		attributes.setTec(tec[0]);
		attributes.setQTec(tec[1]);

		int[] agr = parseAttribute(texts[6]);
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
