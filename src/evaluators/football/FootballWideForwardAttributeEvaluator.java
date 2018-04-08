package evaluators.football;

public class FootballWideForwardAttributeEvaluator
	extends FootballAttributeEvaluator
{
	private static final String NAME = "Wide Forward";

	private static final int GOA = 0;
	private static final int DEF = 0;
	private static final int MID = 0;
	private static final int OFF = PRIMARY;
	private static final int SHO = HIGH;
	private static final int PAS = MEDIUM;
	private static final int TEC = HIGH;
	private static final int SPE = HIGH;
	private static final int HEA = LOW;
	
	public FootballWideForwardAttributeEvaluator()
	{
		super(NAME, GOA, DEF, MID, OFF, SHO, PAS, TEC, SPE, HEA);
	}
}
