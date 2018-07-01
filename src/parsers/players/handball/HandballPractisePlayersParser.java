package parsers.players.handball;

import java.util.Arrays;
import java.util.List;

public class HandballPractisePlayersParser
	extends HandballPlayersParser
{
	public HandballPractisePlayersParser()
	{
		super(
			createPattern(
				name(),
				ignore(), // Position
				age(),
				ignore(), // Scouting status
				cl(),
				attributesWithQualities(),
				training()),
			false,
			true,
			false,
			true);
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
