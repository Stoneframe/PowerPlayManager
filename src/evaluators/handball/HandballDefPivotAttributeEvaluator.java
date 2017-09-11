package evaluators.handball;

public class HandballDefPivotAttributeEvaluator extends HandballAttributeEvaluator
{
	private static final String NAME = "Def. Pivot";

	private static final int GOA = 0;
	private static final int FIP = 100;
	private static final int SHO = 0;
	private static final int BLK = 50;
	private static final int PAS = 30;
	private static final int TEC = 50;
	private static final int SPE = 30;
	private static final int AGR = 70;

	public HandballDefPivotAttributeEvaluator()
	{
		super(NAME, GOA, FIP, SHO, BLK, PAS, TEC, SPE, AGR);
	}
}
