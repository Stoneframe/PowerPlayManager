package parsers.players.icehockey;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.icehockey.IceHockeyAttributes;
import parsers.players.AbstractPlayersParser;

public abstract class IceHockeyPlayersParser
	extends AbstractPlayersParser<IceHockeyAttributes>
{

	protected IceHockeyPlayersParser(
			Pattern regexPattern,
			boolean includeSide,
			boolean includeQualities,
			boolean includeExperience,
			boolean includeTraining)
	{
		super(regexPattern, includeSide, includeQualities, includeExperience, includeTraining);
	}

	@Override
	protected IceHockeyAttributes createAttributes(Matcher matcher, boolean includeQuality)
	{
		return null;
	}
}
