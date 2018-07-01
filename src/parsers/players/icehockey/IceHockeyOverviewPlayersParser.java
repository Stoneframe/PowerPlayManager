package parsers.players.icehockey;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class IceHockeyOverviewPlayersParser
	extends IceHockeyPlayersParser
{
	private static final Pattern REGEX_PATTERN = createPattern(
		name(),
		ignore(), // Position
		age(),
		ignore(), // Scouting status
		ignore(), // Average quality
		cl(),
		attributes(),
		experience(),
		ignore(), // Total rating
		side());

	private static final boolean INCLUDE_CL = true;
	private static final boolean INCLUDE_SIDE = true;
	private static final boolean INCLUDE_QUALITIES = false;
	private static final boolean INCLUDE_EXPERIENCE = true;
	private static final boolean INCLUDE_CHEMISTRY = false;
	private static final boolean INCLUDE_ENERGY = false;
	private static final boolean INCLUDE_TRAINING = false;

	public IceHockeyOverviewPlayersParser()
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
		return "Overview";
	}

	@Override
	protected List<String> toSinglePlayerPerLine(String textToParse)
	{
		return Arrays.asList(textToParse.split("\n"));
	}
}
