package parsers.players.handball;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import model.Player;
import model.handball.HandballAttributes;
import parsers.ParseException;

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
				training()));
	}

	@Override
	public String getName()
	{
		return "Practise (Pro)";
	}

	@Override
	public List<Player<HandballAttributes>> parsePlayers(String textToParse) throws ParseException
	{
		List<String> lines = new LinkedList<>();

		String[] split = textToParse.split("\n");

		for (int i = 0; i < split.length; i += 17)
		{
			String line = String.join("\t", Arrays.copyOfRange(split, i, i + 17));

			System.out.println(line);

			lines.add(line);
		}

		return parsePlayers(lines, false, true, false, true);
	}
}
