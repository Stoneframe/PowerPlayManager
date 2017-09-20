package evaluators.handball;

public class HandballWingAttributeEvaluator extends HandballAttributeEvaluator
{
	private static final String NAME = "Wing";

	private static final int GOA = 0;
	private static final int FIP = 100;
	private static final int SHO = 30;
	private static final int BLK = 30;
	private static final int PAS = 30;
	private static final int TEC = 50;
	private static final int SPE = 70;
	private static final int AGR = 30;

	public HandballWingAttributeEvaluator()
	{
		super(NAME, GOA, FIP, SHO, BLK, PAS, TEC, SPE, AGR);
	}
}
