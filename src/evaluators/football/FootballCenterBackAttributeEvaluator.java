package evaluators.football;

public class FootballCenterBackAttributeEvaluator
	extends FootballAttributeEvaluator
{
	private static final String NAME = "Center Back";

	private static final int GOA = 0;
	private static final int DEF = PRIMARY;
	private static final int MID = 0;
	private static final int OFF = 0;
	private static final int SHO = LOW;
	private static final int PAS = MEDIUM;
	private static final int TEC = MEDIUM;
	private static final int SPE = MEDIUM;
	private static final int HEA = MEDIUM;
	
	public FootballCenterBackAttributeEvaluator()
	{
		super(NAME, GOA, DEF, MID, OFF, SHO, PAS, TEC, SPE, HEA);
	}
}
