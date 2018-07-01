package parsers.players.icehockey;

import java.util.Arrays;
import java.util.List;

public class IceHockeyFormationPlayersParser
	extends IceHockeyPlayersParser
{
	public IceHockeyFormationPlayersParser()
	{
		super(
			createPattern(
				ignore(),
				name(),
				age(),
				side(),
				attributes(),
				experience(),
				ignore(),
				ignore()),
			false,
			true,
			false,
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
		return Arrays.asList(textToParse.split("\n"));
	}
}
