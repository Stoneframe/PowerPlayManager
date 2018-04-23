package evaluators.football;

public class FootballWideMidfielderAttributeEvaluator
	extends FootballAttributeEvaluator
{
	private static final String NAME = "Wide Midfielder";

	private static final int GOA = 0;
	private static final int DEF = 0;
	private static final int MID = PRIMARY;
	private static final int OFF = 0;
	private static final int SHO = MEDIUM;
	private static final int PAS = MEDIUM;
	private static final int TEC = MEDIUM;
	private static final int SPE = HIGH;
	private static final int HEA = LOW;

	public FootballWideMidfielderAttributeEvaluator()
	{
		super(NAME, GOA, DEF, MID, OFF, SHO, PAS, TEC, SPE, HEA);
	}
}
