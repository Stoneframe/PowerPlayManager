package parsers.players.football;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.football.FootballAttributes;
import parsers.players.RegexPlayersParser;

public abstract class FootballPlayersParser
	extends RegexPlayersParser<FootballAttributes>
{
	protected FootballPlayersParser(
			Pattern regexPattern,
			boolean includeCL,
			boolean includeSide,
			boolean includeQualities,
			boolean includeExperience,
			boolean includeChemistry,
			boolean includeEnergy,
			boolean includeTraining)
	{
		super(
			regexPattern,
			includeCL,
			includeSide,
			includeQualities,
			includeExperience,
			includeChemistry,
			includeEnergy,
			includeTraining);
	}

	@Override
	protected FootballAttributes createAttributes(Matcher matcher, boolean includeQuality)
	{
		FootballAttributes attributes = new FootballAttributes();

		attributes.getGoa().setRating(Integer.parseInt(matcher.group("goa")));
		attributes.getDef().setRating(Integer.parseInt(matcher.group("def")));
		attributes.getMid().setRating(Integer.parseInt(matcher.group("mid")));
		attributes.getOff().setRating(Integer.parseInt(matcher.group("off")));
		attributes.getSho().setRating(Integer.parseInt(matcher.group("sho")));
		attributes.getPas().setRating(Integer.parseInt(matcher.group("pas")));
		attributes.getTec().setRating(Integer.parseInt(matcher.group("tec")));
		attributes.getSpe().setRating(Integer.parseInt(matcher.group("spe")));
		attributes.getHea().setRating(Integer.parseInt(matcher.group("hea")));

		if (includeQuality)
		{
			attributes.getGoa().setQuality(Integer.parseInt(matcher.group("qgoa")));
			attributes.getDef().setQuality(Integer.parseInt(matcher.group("qdef")));
			attributes.getMid().setQuality(Integer.parseInt(matcher.group("qmid")));
			attributes.getOff().setQuality(Integer.parseInt(matcher.group("qoff")));
			attributes.getSho().setQuality(Integer.parseInt(matcher.group("qsho")));
			attributes.getPas().setQuality(Integer.parseInt(matcher.group("qpas")));
			attributes.getTec().setQuality(Integer.parseInt(matcher.group("qtec")));
			attributes.getSpe().setQuality(Integer.parseInt(matcher.group("qspe")));
			attributes.getHea().setQuality(Integer.parseInt(matcher.group("qhea")));
		}

		return attributes;
	}

	protected static String attributes()
	{
		return attributes("goa", "def", "mid", "off", "sho", "pas", "tec", "spe", "hea");
	}

	protected static String attributesWithQualities()
	{
		return attributesWithQualities(
			"goa",
			"def",
			"mid",
			"off",
			"sho",
			"pas",
			"tec",
			"spe",
			"hea");
	}
}
