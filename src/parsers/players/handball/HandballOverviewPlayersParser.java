package parsers.players.handball;

import java.util.Arrays;
import java.util.List;

import model.Player;
import model.handball.HandballAttributes;
import parsers.ParseException;

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
				side()));
	}

	@Override
	public String getName()
	{
		return "Overview";
	}

	@Override
	public List<Player<HandballAttributes>> parsePlayers(String textToParse)
			throws ParseException
	{
		List<String> lines = Arrays.asList(textToParse.split("\n"));

		return parsePlayers(lines, true, false, true, false);
	}
}
