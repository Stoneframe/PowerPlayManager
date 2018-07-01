package parsers.players.handball;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Player;
import model.Side;
import model.handball.HandballAttributes;
import model.handball.HandballPlayer;
import parsers.ParseException;
import parsers.SideParser;
import parsers.players.PlayersParser;

public abstract class HandballPlayersParser
	extends PlayersParser<HandballAttributes>
{
	private final Pattern regexPattern;

	protected HandballPlayersParser(Pattern regexPattern)
	{
		this.regexPattern = regexPattern;
	}

	protected static String attributes()
	{
		return attributes("goa", "fip", "sho", "blk", "pas", "tec", "spe", "agr");
	}

	protected static String attributesWithQualities()
	{
		return attributesWithQualities("goa", "fip", "sho", "blk", "pas", "tec", "spe", "agr");
	}

	protected List<Player<HandballAttributes>> parsePlayers(
			List<String> lines,
			boolean includeSide,
			boolean includeQualities,
			boolean includeExperience,
			boolean includeTraining)
			throws ParseException
	{
		List<Player<HandballAttributes>> players = new LinkedList<Player<HandballAttributes>>();

		for (String line : lines)
		{
			Matcher matcher = regexPattern.matcher(line);

			if (matcher.find())
			{
				HandballPlayer player = createPlayer(
					matcher,
					includeSide,
					includeQualities,
					includeExperience,
					includeTraining);

				players.add(player);
			}
			else
			{
				throw new ParseException();
			}
		}

		return players;
	}

	private HandballPlayer createPlayer(
			Matcher matcher,
			boolean includeSide,
			boolean includeQualities,
			boolean includeExperience,
			boolean includeTraining)
	{
		HandballAttributes attributes = createAttributes(matcher, includeQualities);

		return new HandballPlayer(
				matcher.group("name"),
				Integer.parseInt(matcher.group("age")),
				Integer.parseInt(matcher.group("cl")),
				includeSide ? SideParser.parseSide(matcher.group("side")) : Side.UNKNOWN,
				attributes,
				includeExperience ? Integer.parseInt(matcher.group("experience")) : 0,
				0,
				0,
				includeTraining ? Double.parseDouble(matcher.group("training")) : 0);
	}

	private HandballAttributes createAttributes(Matcher matcher, boolean includeQuality)
	{
		HandballAttributes attributes = new HandballAttributes();

		attributes.setGoa(Integer.parseInt(matcher.group("goa")));
		attributes.setFip(Integer.parseInt(matcher.group("fip")));
		attributes.setSho(Integer.parseInt(matcher.group("sho")));
		attributes.setBlk(Integer.parseInt(matcher.group("blk")));
		attributes.setPas(Integer.parseInt(matcher.group("pas")));
		attributes.setTec(Integer.parseInt(matcher.group("tec")));
		attributes.setSpe(Integer.parseInt(matcher.group("spe")));
		attributes.setAgr(Integer.parseInt(matcher.group("agr")));

		if (includeQuality)
		{
			attributes.setQGoa(Integer.parseInt(matcher.group("qgoa")));
			attributes.setQFip(Integer.parseInt(matcher.group("qfip")));
			attributes.setQSho(Integer.parseInt(matcher.group("qsho")));
			attributes.setQBlk(Integer.parseInt(matcher.group("qblk")));
			attributes.setQPas(Integer.parseInt(matcher.group("qpas")));
			attributes.setQTec(Integer.parseInt(matcher.group("qtec")));
			attributes.setQSpe(Integer.parseInt(matcher.group("qspe")));
			attributes.setQAgr(Integer.parseInt(matcher.group("qagr")));
		}

		return attributes;
	}
}