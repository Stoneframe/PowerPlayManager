package parsers.players.icehockey;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Player;
import model.Side;
import model.icehockey.IceHockeyAttributes;
import model.icehockey.IceHockeyPlayer;
import parsers.SideParser;
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

		attributes.setGoa(Integer.parseInt(matcher.group("goa")));
		attributes.setDef(Integer.parseInt(matcher.group("def")));
		attributes.setOff(Integer.parseInt(matcher.group("off")));
		attributes.setSho(Integer.parseInt(matcher.group("sho")));
		attributes.setPas(Integer.parseInt(matcher.group("pas")));
		attributes.setTec(Integer.parseInt(matcher.group("tec")));
		attributes.setAgr(Integer.parseInt(matcher.group("agr")));

		if (includeQuality)
		{
			attributes.setQGoa(Integer.parseInt(matcher.group("qgoa")));
			attributes.setQDef(Integer.parseInt(matcher.group("qdef")));
			attributes.setQOff(Integer.parseInt(matcher.group("qoff")));
			attributes.setQSho(Integer.parseInt(matcher.group("qsho")));
			attributes.setQPas(Integer.parseInt(matcher.group("qpas")));
			attributes.setQTec(Integer.parseInt(matcher.group("qtec")));
			attributes.setQAgr(Integer.parseInt(matcher.group("qagr")));
		}

		return attributes;
	}
	
	@Override
	protected Player<IceHockeyAttributes> createPlayer(Matcher matcher)
	{
		IceHockeyAttributes attributes = createAttributes(matcher, includeQualities);

		return new IceHockeyPlayer(
				matcher.group("name"),
				Integer.parseInt(matcher.group("age")),
				includeCL ? Integer.parseInt(matcher.group("cl")) : 0,
				includeSide ? SideParser.parseSide(matcher.group("side")) : Side.UNKNOWN,
				attributes,
				includeExperience ? Integer.parseInt(matcher.group("experience")) : 0,
				includeChemistry ? Integer.parseInt(matcher.group("chemistry")) : 0,
				includeEnergy ? Integer.parseInt(matcher.group("energy")) : 100,
				includeTraining ? Double.parseDouble(matcher.group("training")) : 0);
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
