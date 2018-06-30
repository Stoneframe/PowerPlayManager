package parsers.players.handball;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Player;
import model.handball.HandballAttributes;
import model.handball.HandballPlayer;
import parsers.ParseException;
import parsers.SideParser;
import parsers.players.PlayersParser;

public class HandballMarketPlayersParser
	extends PlayersParser<HandballAttributes>
{
	private static final Pattern REGEX_PATTERN = createPattern(
		name(),
		ignore(), // Deadline
		ignore(), // Cost
		age(),
		ignore(), // Scouting status
		ignore(), // Average quality
		cl(),
		attributesWithQualities("goa", "fip", "sho", "blk", "pas", "tec", "spe", "agr"),
		experience(),
		ignore(), // Total rating
		side());

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
			List<Player<HandballAttributes>> players = new LinkedList<Player<HandballAttributes>>();

			String[] lines = textToParse.split("\n");

//			for (int i = 0; i < lines.length; i += 4)
//			{
//				HandballPlayer player = new HandballPlayer(
//						parseName(lines[i]),
//						parseAge(lines[i + 3].trim().split("\t")[0].trim()),
//						parseCL(lines[i + 3].trim().split("\t")[3]),
//						SideParser.parseSide(lines[i + 3].trim().split("\t")[14]),
//						parseAttributes(Arrays.copyOfRange(lines[i + 3].trim().split("\t"), 4, 12)),
//						0,
//						0,
//						0,
//						0);
//
//				players.add(player);
//			}

			System.out.println(REGEX_PATTERN.pattern());

			for (int i = 0; i < lines.length; i += 4)
			{
				String line = String.join("\t", Arrays.copyOfRange(lines, i, i + 4));

				System.out.println(line);

				Matcher matcher = REGEX_PATTERN.matcher(line);

				if (matcher.find())
				{
					System.out.println("name: " + matcher.group("name"));

					System.out.println("cl: " + matcher.group("cl"));

					System.out.println("goa: " + matcher.group("goa"));
					System.out.println("qgoa: " + matcher.group("qgoa"));
					System.out.println("fip: " + matcher.group("fip"));
					System.out.println("qfip: " + matcher.group("qfip"));
					System.out.println("sho: " + matcher.group("sho"));
					System.out.println("qsho: " + matcher.group("qsho"));
					System.out.println("blk: " + matcher.group("blk"));
					System.out.println("qblk: " + matcher.group("qblk"));
					System.out.println("pas: " + matcher.group("pas"));
					System.out.println("qpas: " + matcher.group("qpas"));
					System.out.println("tec: " + matcher.group("tec"));
					System.out.println("qtec: " + matcher.group("qtec"));
					System.out.println("spe: " + matcher.group("spe"));
					System.out.println("qspe: " + matcher.group("qspe"));
					System.out.println("agr: " + matcher.group("agr"));
					System.out.println("qagr: " + matcher.group("qagr"));

					System.out.println("exp: " + matcher.group("experience"));

					System.out.println("side: " + matcher.group("side"));
					
					HandballAttributes attributes = new HandballAttributes();
					
					attributes.setGoa(Integer.parseInt(matcher.group("goa")));
					attributes.setQGoa(Integer.parseInt(matcher.group("qgoa")));
					
					attributes.setFip(Integer.parseInt(matcher.group("fip")));
					attributes.setQFip(Integer.parseInt(matcher.group("qfip")));
					
					attributes.setSho(Integer.parseInt(matcher.group("sho")));
					attributes.setQSho(Integer.parseInt(matcher.group("qsho")));
					
					attributes.setBlk(Integer.parseInt(matcher.group("blk")));
					attributes.setQBlk(Integer.parseInt(matcher.group("qblk")));
					
					attributes.setPas(Integer.parseInt(matcher.group("pas")));
					attributes.setQPas(Integer.parseInt(matcher.group("qpas")));
					
					attributes.setTec(Integer.parseInt(matcher.group("tec")));
					attributes.setQTec(Integer.parseInt(matcher.group("qtec")));
					
					attributes.setSpe(Integer.parseInt(matcher.group("spe")));
					attributes.setQSpe(Integer.parseInt(matcher.group("qspe")));
					
					attributes.setAgr(Integer.parseInt(matcher.group("agr")));
					attributes.setQAgr(Integer.parseInt(matcher.group("qagr")));
					
					HandballPlayer player = new HandballPlayer(
						matcher.group("name"), 
						Integer.parseInt(matcher.group("age")), 
						Integer.parseInt(matcher.group("cl")),
						SideParser.parseSide(matcher.group("side")),
						attributes,
						Integer.parseInt(matcher.group("experience")),
						0,
						0,
						0);
					
					players.add(player);
				}
				else
				{
					System.out.println("No match");
				}
			}

			return players;
		}
		catch (Exception e)
		{
			e.printStackTrace();
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

	private static HandballAttributes parseAttributes(String[] texts)
	{
		HandballAttributes attributes = new HandballAttributes();

		int[] goa = parseAttribute(texts[0].trim());
		attributes.setGoa(goa[0]);
		attributes.setQGoa(goa[1]);

		int[] fip = parseAttribute(texts[1].trim());
		attributes.setFip(fip[0]);
		attributes.setQFip(fip[1]);

		int[] sho = parseAttribute(texts[2].trim());
		attributes.setSho(sho[0]);
		attributes.setQSho(sho[1]);

		int[] blk = parseAttribute(texts[3].trim());
		attributes.setBlk(blk[0]);
		attributes.setQBlk(blk[1]);

		int[] pas = parseAttribute(texts[4].trim());
		attributes.setPas(pas[0]);
		attributes.setQPas(pas[1]);

		int[] tec = parseAttribute(texts[5].trim());
		attributes.setTec(tec[0]);
		attributes.setQTec(tec[1]);

		int[] spe = parseAttribute(texts[6].trim());
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
		int quality = Integer.parseInt(text.substring(text.length() - 2, text.length()));

		return new int[]
		{
				rating,
				quality
		};
	}
}
