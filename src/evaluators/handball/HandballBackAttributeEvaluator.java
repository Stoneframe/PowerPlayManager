package evaluators.handball;

public class HandballBackAttributeEvaluator extends HandballAttributeEvaluator
{
	private static final String NAME = "Back";

	private static final int GOA = 0;
	private static final int FIP = 100;
	private static final int SHO = 80;
	private static final int BLK = 40;
	private static final int PAS = 80;
	private static final int TEC = 60;
	private static final int SPE = 40;
	private static final int AGR = 40;

	public HandballBackAttributeEvaluator()
	{
		super(NAME, GOA, FIP, SHO, BLK, PAS, TEC, SPE, AGR);
	}
}
