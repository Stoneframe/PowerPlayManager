package parsers.players.handball;

import java.util.regex.Pattern;

public class HandballMarketPlayersParser
	extends HandballPlayersParser
{
	private static final int NUMBER_OF_LINES = 4;
	private static final Pattern REGEX_PATTERN = createPattern(
		name(),
		ignore(), // Deadline
		ignore(), // Cost
		age(),
		ignore(), // Scouting status
		ignore(), // Average quality
		cl(),
		attributesWithQualities(),
		experience(),
		ignore(), // Total rating
		side());

	private static final boolean INCLUDE_CL = true;
	private static final boolean INCLUDE_SIDE = true;
	private static final boolean INCLUDE_QUALITIES = true;
	private static final boolean INCLUDE_EXPERIENCE = true;
	private static final boolean INCLUDE_CHEMISTRY = false;
	private static final boolean INCLUDE_ENERGY = false;
	private static final boolean INCLUDE_TRAINING = false;

	public HandballMarketPlayersParser()
	{
		super(
			NUMBER_OF_LINES,
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
		return "Market";
	}
}
