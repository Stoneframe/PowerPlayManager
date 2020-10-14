package parsers.players.icehockey;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

public class IceHockeyPractiseProPlayersParser
	extends IceHockeyPlayersParser
{
	private static final Pattern REGEX_PATTERN = createPattern(
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
		training());

	private static final boolean INCLUDE_CL = true;
	private static final boolean INCLUDE_SIDE = false;
	private static final boolean INCLUDE_QUALITIES = true;
	private static final boolean INCLUDE_EXPERIENCE = false;
	private static final boolean INCLUDE_CHEMISTRY = false;
	private static final boolean INCLUDE_ENERGY = false;
	private static final boolean INCLUDE_TRAINING = true;

	public IceHockeyPractiseProPlayersParser()
	{
		super(
			REGEX_PATTERN,
			INCLUDE_CL,
			INCLUDE_SIDE,
			INCLUDE_QUALITIES,
			INCLUDE_EXPERIENCE,
			INCLUDE_CHEMISTRY,
			INCLUDE_ENERGY,
			INCLUDE_TRAINING);
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

		for (int i = 0; i < split.length; i += 16)
		{
			lines.add(String.join("\t", Arrays.copyOfRange(split, i, i + 15)));
		}

		return lines;
	}
}
