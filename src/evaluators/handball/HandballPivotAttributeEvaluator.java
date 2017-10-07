package evaluators.handball;

public class HandballPivotAttributeEvaluator
	extends HandballAttributeEvaluator
{
	private static final String NAME = "Pivot";

	private static final int GOA = 0;
	private static final int FIP = 100;
	private static final int SHO = 35;
	private static final int BLK = 35;
	private static final int PAS = 30;
	private static final int TEC = 50;
	private static final int SPE = 30;
	private static final int AGR = 70;

	public HandballPivotAttributeEvaluator()
	{
		super(NAME, GOA, FIP, SHO, BLK, PAS, TEC, SPE, AGR);
	}
}
