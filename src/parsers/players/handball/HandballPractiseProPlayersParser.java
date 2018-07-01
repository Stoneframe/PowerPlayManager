package parsers.players.handball;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class HandballPractiseProPlayersParser
	extends HandballPlayersParser
{
	public HandballPractiseProPlayersParser()
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
				attributeWithQuality("fip"),
				ignore(), // Fip training
				attributeWithQuality("sho"),
				ignore(), // Sho training
				attributeWithQuality("blk"),
				ignore(), // Blk training
				attributeWithQuality("pas"),
				ignore(), // Pas training
				attributeWithQuality("tec"),
				ignore(), // Tec training
				attributeWithQuality("spe"),
				ignore(), // Spe training
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

		for (int i = 0; i < split.length; i += 17)
		{
			lines.add(String.join("\t", Arrays.copyOfRange(split, i, i + 17)));
		}

		return lines;
	}
}
