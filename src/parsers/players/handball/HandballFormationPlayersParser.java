package parsers.players.handball;

import java.util.regex.Pattern;

public class HandballFormationPlayersParser
	extends HandballPlayersParser
{
	private static final int NUMBER_OF_LINES = 1;
	private static final Pattern REGEX_PATTERN = createPattern(
		ignore(), // Checkbox
		name(),
		age(),
		attributes(),
		experience(),
		ignore(), // Total rating
		side(),
		chemistry(),
		energy());

	private static final boolean INCLUDE_CL = false;
	private static final boolean INCLUDE_SIDE = true;
	private static final boolean INCLUDE_QUALITIES = false;
	private static final boolean INCLUDE_EXPERIENCE = true;
	private static final boolean INCLUDE_CHEMISTRY = true;
	private static final boolean INCLUDE_ENERGY = true;
	private static final boolean INCLUDE_TRAINING = false;

	public HandballFormationPlayersParser()
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
		return "Formation";
	}
}
