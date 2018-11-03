package parsers.players.handball;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.handball.HandballAttributes;
import parsers.players.RegexPlayersParser;

public abstract class HandballPlayersParser
	extends RegexPlayersParser<HandballAttributes>
{
	protected HandballPlayersParser(
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
	protected HandballAttributes createAttributes(Matcher matcher, boolean includeQuality)
	{
		HandballAttributes attributes = new HandballAttributes();

		attributes.getGoa().setRating(Integer.parseInt(matcher.group("goa")));
		attributes.getFip().setRating(Integer.parseInt(matcher.group("fip")));
		attributes.getSho().setRating(Integer.parseInt(matcher.group("sho")));
		attributes.getBlk().setRating(Integer.parseInt(matcher.group("blk")));
		attributes.getPas().setRating(Integer.parseInt(matcher.group("pas")));
		attributes.getTec().setRating(Integer.parseInt(matcher.group("tec")));
		attributes.getSpe().setRating(Integer.parseInt(matcher.group("spe")));
		attributes.getAgr().setRating(Integer.parseInt(matcher.group("agr")));

		if (includeQuality)
		{
			attributes.getGoa().setQuality(Integer.parseInt(matcher.group("qgoa")));
			attributes.getFip().setQuality(Integer.parseInt(matcher.group("qfip")));
			attributes.getSho().setQuality(Integer.parseInt(matcher.group("qsho")));
			attributes.getBlk().setQuality(Integer.parseInt(matcher.group("qblk")));
			attributes.getPas().setQuality(Integer.parseInt(matcher.group("qpas")));
			attributes.getTec().setQuality(Integer.parseInt(matcher.group("qtec")));
			attributes.getSpe().setQuality(Integer.parseInt(matcher.group("qspe")));
			attributes.getAgr().setQuality(Integer.parseInt(matcher.group("qagr")));
		}

		return attributes;
	}

	protected static String attributes()
	{
		return attributes("goa", "fip", "sho", "blk", "pas", "tec", "spe", "agr");
	}

	protected static String attributesWithQualities()
	{
		return attributesWithQualities("goa", "fip", "sho", "blk", "pas", "tec", "spe", "agr");
	}
}