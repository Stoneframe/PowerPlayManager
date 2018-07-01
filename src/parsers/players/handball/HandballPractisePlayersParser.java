package parsers.players.handball;

import java.util.Arrays;
import java.util.List;

import model.Player;
import model.handball.HandballAttributes;
import parsers.ParseException;

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
				training()));
	}

	@Override
	public String getName()
	{
		return "Practise";
	}

	@Override
	public List<Player<HandballAttributes>> parsePlayers(String textToParse)
			throws ParseException
	{
		List<String> lines = Arrays.asList(textToParse.split("\n"));

		return parsePlayers(lines, false, true, false, true);
	}
}
