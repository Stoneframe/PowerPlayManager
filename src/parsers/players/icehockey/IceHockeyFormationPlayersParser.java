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
				ignore(), // Checkbox
				name(),
				age(),
				side(),
				attributes(),
				experience(),
				energy(),
				chemistry()),
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
		return Arrays.asList(textToParse.split("\n"));
	}
}
