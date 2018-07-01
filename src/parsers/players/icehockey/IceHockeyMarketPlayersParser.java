package parsers.players.icehockey;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class IceHockeyMarketPlayersParser
	extends IceHockeyPlayersParser
{
	public IceHockeyMarketPlayersParser()
	{
		super(
			createPattern(
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
				side()),
			true,
			true,
			true,
			true,
			false);
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
