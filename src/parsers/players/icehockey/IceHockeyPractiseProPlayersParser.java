package parsers.players.icehockey;

import java.util.regex.Pattern;

public class IceHockeyPractiseProPlayersParser
	extends IceHockeyPlayersParser
{
	private static final int NUMBER_OF_LINES = 16;
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
			NUMBER_OF_LINES,
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
}
