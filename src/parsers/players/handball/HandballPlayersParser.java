package parsers.players.handball;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.handball.HandballAttributes;
import parsers.players.RegexPlayersParser;

public abstract class HandballPlayersParser
	extends RegexPlayersParser<HandballAttributes>
{
	protected HandballPlayersParser(
		int numberOfLines,
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
			numberOfLines,
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

	protected static String attributes()
	{
		return attributes("goa", "fip", "sho", "blk", "pas", "tec", "spe", "agr");
	}

	protected static String attributesWithQualities()
	{
		return attributesWithQualities("goa", "fip", "sho", "blk", "pas", "tec", "spe", "agr");
	}
}