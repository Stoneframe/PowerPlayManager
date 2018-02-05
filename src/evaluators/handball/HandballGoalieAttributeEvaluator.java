package evaluators.handball;

public class HandballGoalieAttributeEvaluator
	extends HandballAttributeEvaluator
{
	private static final String NAME = "Goalie";

	private static final int GOA = 100;
	private static final int FIP = 0;
	private static final int SHO = 0;
	private static final int BLK = 70;
	private static final int PAS = MEDIUM;
	private static final int TEC = LOW;
	private static final int SPE = LOW;
	private static final int AGR = 0;

	public HandballGoalieAttributeEvaluator()
	{
		super(NAME, GOA, FIP, SHO, BLK, PAS, TEC, SPE, AGR);
	}
}
