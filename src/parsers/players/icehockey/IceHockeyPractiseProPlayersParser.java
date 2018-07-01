package parsers.players.icehockey;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class IceHockeyPractiseProPlayersParser
	extends IceHockeyPlayersParser
{
	public IceHockeyPractiseProPlayersParser()
	{
		super(
			createPattern(
				name(),
				ignore(), // Position
				age(),
				ignore(), // Scouting status
				cl(),
				attributeWithQuality("goa"),
				ignore(), // Goa training
				attributeWithQuality("def"),
				ignore(), // Def training
				attributeWithQuality("off"),
				ignore(), // Off training
				attributeWithQuality("sho"),
				ignore(), // Sho training
				attributeWithQuality("pas"),
				ignore(), // Pas training
				attributeWithQuality("tec"),
				ignore(), // Tec training
				attributeWithQuality("agr"),
				ignore(), // Agr training
				training()),
			false,
			true,
			false,
			true);
	}

	@Override
	public String getName()
	{
		return "Practise (Pro)";
	}

	@Override
	protected List<String> toSinglePlayerPerLine(String textToParse)
	{
		List<String> lines = new LinkedList<>();

		String[] split = textToParse.split("\n");

		for (int i = 0; i < split.length; i += 15)
		{
			lines.add(String.join("\t", Arrays.copyOfRange(split, i, i + 15)));
		}

		return lines;
	}
}
