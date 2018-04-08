package evaluators.football;

public class FootballCenterMidfielderAttributeEvaluator
	extends FootballAttributeEvaluator
{
	private static final String NAME = "Center Midfielder";

	private static final int GOA = 0;
	private static final int DEF = 0;
	private static final int MID = PRIMARY;
	private static final int OFF = 0;
	private static final int SHO = MEDIUM;
	private static final int PAS = HIGH;
	private static final int TEC = HIGH;
	private static final int SPE = LOW;
	private static final int HEA = LOW;
	
	public FootballCenterMidfielderAttributeEvaluator()
	{
		super(NAME, GOA, DEF, MID, OFF, SHO, PAS, TEC, SPE, HEA);
	}
}
