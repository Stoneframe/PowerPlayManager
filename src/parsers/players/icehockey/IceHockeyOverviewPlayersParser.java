package parsers.players.icehockey;

import java.util.Arrays;
import java.util.List;

public class IceHockeyOverviewPlayersParser
	extends IceHockeyPlayersParser
{
	public IceHockeyOverviewPlayersParser()
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
			true,
			false,
			true,
			false,
			false,
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
