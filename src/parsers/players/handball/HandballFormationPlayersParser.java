package parsers.players.handball;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class HandballFormationPlayersParser
	extends HandballPlayersParser
{
	public HandballFormationPlayersParser()
	{
		super(
			createPattern(
				ignore(), // Checkbox
				name(),
				ignore(), // Empty
				ignore(), // Position
				ignore(), // Empty
				age(),
				attributes(),
				experience(),
				ignore(), // Total rating
				side(),
				chemistry(),
				energy()),
			false,
			true,
			false,
			true,
			true,
			true,
			false);
	}

	@Override
	public String getName()
	{
		return "Formation";
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
