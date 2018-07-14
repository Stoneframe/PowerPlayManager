package parsers.players.icehockey;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

public class IceHockeyMarketPlayersParser
	extends IceHockeyPlayersParser
{
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

	public IceHockeyMarketPlayersParser()
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
		return "Market";
	}

	@Override
	protected List<String> toSinglePlayerPerLine(String textToParse)
	{
		List<String> lines = new LinkedList<>();

		String[] split = textToParse.split("\n");

		for (int i = 0; i < split.length; i += 4)
		{
			lines.add(String.join("\t", Arrays.copyOfRange(split, i, i + 4)));
		}

		return lines;
	}
}
