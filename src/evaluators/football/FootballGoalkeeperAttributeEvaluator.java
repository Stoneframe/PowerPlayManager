package evaluators.football;

public class FootballGoalkeeperAttributeEvaluator
	extends FootballAttributeEvaluator
{
	private static final String NAME = "Goalkeeper";

	private static final int GOA = PRIMARY;
	private static final int DEF = 0;
	private static final int MID = 0;
	private static final int OFF = 0;
	private static final int SHO = 0;
	private static final int PAS = LOW;
	private static final int TEC = HIGH;
	private static final int SPE = HIGH;
	private static final int HEA = LOW;

	public FootballGoalkeeperAttributeEvaluator()
	{
		super(NAME, GOA, DEF, MID, OFF, SHO, PAS, TEC, SPE, HEA);
	}
}
