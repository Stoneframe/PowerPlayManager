package evaluators.handball;

public class HandballDefBackAttributeEvaluator
	extends HandballAttributeEvaluator
{
	private static final String NAME = "Def. Back";

	private static final int GOA = 0;
	private static final int FIP = PRIMARY;
	private static final int SHO = 0;
	private static final int BLK = 100;
	private static final int PAS = HIGH;
	private static final int TEC = MEDIUM;
	private static final int SPE = LOW;
	private static final int AGR = LOW;

	public HandballDefBackAttributeEvaluator()
	{
		super(NAME, GOA, FIP, SHO, BLK, PAS, TEC, SPE, AGR);
	}
}
