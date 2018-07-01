package parsers.players.handball;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import model.Player;
import model.handball.HandballAttributes;
import parsers.ParseException;

public class HandballMarketPlayersParser
	extends HandballPlayersParser
{
	public HandballMarketPlayersParser()
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
				side()));
	}

	@Override
	public String getName()
	{
		return "Market";
	}

	@Override
	public List<Player<HandballAttributes>> parsePlayers(String textToParse) throws ParseException
	{
		List<String> lines = new LinkedList<>();

		String[] split = textToParse.split("\n");

		for (int i = 0; i < split.length; i += 4)
		{
			lines.add(String.join("\t", Arrays.copyOfRange(split, i, i + 4)));
		}

		return parsePlayers(lines, true, true, true, false);
	}
}
