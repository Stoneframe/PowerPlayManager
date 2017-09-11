package evaluators.handball;

public class HandballBackAttributeEvaluator extends HandballAttributeEvaluator
{
	private static final String NAME = "Back";

	private static final int GOA = 0;
	private static final int FIP = 100;
	private static final int SHO = 70;
	private static final int BLK = 30;
	private static final int PAS = 70;
	private static final int TEC = 50;
	private static final int SPE = 30;
	private static final int AGR = 30;

	public HandballBackAttributeEvaluator()
	{
		super(NAME, GOA, FIP, SHO, BLK, PAS, TEC, SPE, AGR);
	}
}
