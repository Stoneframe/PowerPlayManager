package evaluators.handball;

public abstract class HandballPivotAttributeEvaluator
	extends HandballAttributeEvaluator
{
	private static final int GOA = 0;
	private static final int FIP = 100;
	private static final int PAS = 30;
	private static final int TEC = 60;
	private static final int SPE = 30;
	private static final int AGR = 77;

	protected HandballPivotAttributeEvaluator(String name, int sho, int blk)
	{
		super(name, GOA, FIP, sho, blk, PAS, TEC, SPE, AGR);
	}
}
