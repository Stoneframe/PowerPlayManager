package evaluators.handball;

public class HandballBackAttributeEvaluator
	extends HandballAttributeEvaluator
{
	private static final String NAME = "Back";

	private static final int GOA = 0;
	private static final int FIP = PRIMARY;
	private static final int SHO = 50;
	private static final int BLK = 50;
	private static final int PAS = HIGH;
	private static final int TEC = MEDIUM;
	private static final int SPE = LOW;
	private static final int AGR = LOW;

	public HandballBackAttributeEvaluator()
	{
		super(NAME, GOA, FIP, SHO, BLK, PAS, TEC, SPE, AGR);
	}
}
