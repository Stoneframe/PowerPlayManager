package parsers.players.football;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Player;
import model.Side;
import model.football.FootballAttributes;
import model.football.FootballPlayer;
import parsers.SideParser;
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

		attributes.setGoa(Integer.parseInt(matcher.group("goa")));
		attributes.setDef(Integer.parseInt(matcher.group("def")));
		attributes.setMid(Integer.parseInt(matcher.group("mid")));
		attributes.setOff(Integer.parseInt(matcher.group("off")));
		attributes.setSho(Integer.parseInt(matcher.group("sho")));
		attributes.setPas(Integer.parseInt(matcher.group("pas")));
		attributes.setTec(Integer.parseInt(matcher.group("tec")));
		attributes.setSpe(Integer.parseInt(matcher.group("spe")));
		attributes.setHea(Integer.parseInt(matcher.group("hea")));

		if (includeQuality)
		{
			attributes.setQGoa(Integer.parseInt(matcher.group("qgoa")));
			attributes.setQDef(Integer.parseInt(matcher.group("qdef")));
			attributes.setQMid(Integer.parseInt(matcher.group("qmid")));
			attributes.setQOff(Integer.parseInt(matcher.group("qoff")));
			attributes.setQSho(Integer.parseInt(matcher.group("qsho")));
			attributes.setQPas(Integer.parseInt(matcher.group("qpas")));
			attributes.setQTec(Integer.parseInt(matcher.group("qtec")));
			attributes.setQSpe(Integer.parseInt(matcher.group("qspe")));
			attributes.setQHea(Integer.parseInt(matcher.group("qhea")));
		}

		return attributes;
	}
	
	@Override
	protected Player<FootballAttributes> createPlayer(Matcher matcher)
	{
		FootballAttributes attributes = createAttributes(matcher, includeQualities);

		return new FootballPlayer(
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
