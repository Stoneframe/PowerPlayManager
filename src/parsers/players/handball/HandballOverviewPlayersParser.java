package parsers.players.handball;

import java.util.Arrays;
import java.util.List;

public class HandballOverviewPlayersParser
	extends HandballPlayersParser
{
	public HandballOverviewPlayersParser()
	{
		super(
			createPattern(
				name(),
				ignore(), // Position
				age(),
				ignore(), // Scouting status
				ignore(), // Average quality
				cl(),
				attributes(),
				experience(),
				ignore(), // Total rating
				side()),
			true,
			false,
			true,
			false);
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
