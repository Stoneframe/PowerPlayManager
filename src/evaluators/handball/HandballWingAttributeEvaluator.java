package evaluators.handball;

public class HandballWingAttributeEvaluator extends HandballAttributeEvaluator
{
	private static final String NAME = "Wing";

	private static final int GOA = 0;
	private static final int FIP = 100;
	private static final int SHO = 60;
	private static final int BLK = 40;
	private static final int PAS = 40;
	private static final int TEC = 60;
	private static final int SPE = 80;
	private static final int AGR = 40;

	public HandballWingAttributeEvaluator()
	{
		super(NAME, GOA, FIP, SHO, BLK, PAS, TEC, SPE, AGR);
	}
}