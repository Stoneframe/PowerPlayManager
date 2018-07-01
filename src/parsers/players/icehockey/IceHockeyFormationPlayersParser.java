package parsers.players.icehockey;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class IceHockeyFormationPlayersParser
	extends IceHockeyPlayersParser
{
	private static final Pattern REGEX_PATTERN = createPattern(
		ignore(), // Checkbox
		name(),
		age(),
		side(),
		attributes(),
		experience(),
		energy(),
		chemistry());

	private static final boolean INCLUDE_CL = false;
	private static final boolean INCLUDE_SIDE = true;
	private static final boolean INCLUDE_QUALITIES = false;
	private static final boolean INCLUDE_EXPERIENCE = true;
	private static final boolean INCLUDE_CHEMISTRY = true;
	private static final boolean INCLUDE_ENERGY = true;
	private static final boolean INCLUDE_TRAINING = false;

	public IceHockeyFormationPlayersParser()
	{
		super(
			REGEX_PATTERN,
			INCLUDE_CL,
			INCLUDE_SIDE,
			INCLUDE_QUALITIES,
			INCLUDE_EXPERIENCE,
			INCLUDE_CHEMISTRY,
			INCLUDE_ENERGY,
			INCLUDE_TRAINING);
	}

	@Override
	public String getName()
	{
		return "Formation";
	}

	@Override
	protected List<String> toSinglePlayerPerLine(String textToParse)
	{
		return Arrays.asList(textToParse.split("\n"));
	}
}
