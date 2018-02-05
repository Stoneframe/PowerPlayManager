package evaluators.handball;

public class HandballWingAttributeEvaluator
	extends HandballAttributeEvaluator
{
	private static final String NAME = "Wing";

	private static final int GOA = 0;
	private static final int FIP = PRIMARY;
	private static final int SHO = 21;
	private static final int BLK = 21;
	private static final int PAS = LOW;
	private static final int TEC = MEDIUM;
	private static final int SPE = HIGH;
	private static final int AGR = LOW;

	public HandballWingAttributeEvaluator()
	{
		super(NAME, GOA, FIP, SHO, BLK, PAS, TEC, SPE, AGR);
	}
}
