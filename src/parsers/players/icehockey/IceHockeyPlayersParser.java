package parsers.players.icehockey;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.icehockey.IceHockeyAttributes;
import parsers.players.RegexPlayersParser;

public abstract class IceHockeyPlayersParser
	extends RegexPlayersParser<IceHockeyAttributes>
{
	protected IceHockeyPlayersParser(
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
	protected IceHockeyAttributes createAttributes(Matcher matcher, boolean includeQuality)
	{
		IceHockeyAttributes attributes = new IceHockeyAttributes();

		attributes.getGoa().setRating(Integer.parseInt(matcher.group("goa")));
		attributes.getDef().setRating(Integer.parseInt(matcher.group("def")));
		attributes.getOff().setRating(Integer.parseInt(matcher.group("off")));
		attributes.getSho().setRating(Integer.parseInt(matcher.group("sho")));
		attributes.getPas().setRating(Integer.parseInt(matcher.group("pas")));
		attributes.getTec().setRating(Integer.parseInt(matcher.group("tec")));
		attributes.getAgr().setRating(Integer.parseInt(matcher.group("agr")));

		if (includeQuality)
		{
			attributes.getGoa().setQuality(Integer.parseInt(matcher.group("qgoa")));
			attributes.getDef().setQuality(Integer.parseInt(matcher.group("qdef")));
			attributes.getOff().setQuality(Integer.parseInt(matcher.group("qoff")));
			attributes.getSho().setQuality(Integer.parseInt(matcher.group("qsho")));
			attributes.getPas().setQuality(Integer.parseInt(matcher.group("qpas")));
			attributes.getTec().setQuality(Integer.parseInt(matcher.group("qtec")));
			attributes.getAgr().setQuality(Integer.parseInt(matcher.group("qagr")));
		}

		return attributes;
	}

	protected static String attributes()
	{
		return attributes("goa", "def", "off", "sho", "pas", "tec", "agr");
	}

	protected static String attributesWithQualities()
	{
		return attributesWithQualities("goa", "def", "off", "sho", "pas", "tec", "agr");
	}
}
