package parsers.players.football;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class FootballPractisePlayersParser
	extends FootballPlayersParser
{
	private static final Pattern REGEX_PATTERN = createPattern(
		name(),
		ignore(), // Position
		age(),
		ignore(), // Scouting status
		cl(),
		attributesWithQualities(),
		training());

	private static final boolean INCLUDE_CL = true;
	private static final boolean INCLUDE_SIDE = false;
	private static final boolean INCLUDE_QUALITIES = true;
	private static final boolean INCLUDE_EXPERIENCE = false;
	private static final boolean INCLUDE_CHEMISTRY = false;
	private static final boolean INCLUDE_ENERGY = false;
	private static final boolean INCLUDE_TRAINING = true;

	public FootballPractisePlayersParser()
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
		return "Practise";
	}

	@Override
	protected List<String> toSinglePlayerPerLine(String textToParse)
	{
		return Arrays.asList(textToParse.split("\n"));
	}
}
