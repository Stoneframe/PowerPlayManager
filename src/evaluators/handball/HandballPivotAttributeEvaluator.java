package evaluators.handball;

public class HandballPivotAttributeEvaluator
	extends HandballAttributeEvaluator
{
	private static final String NAME = "Pivot";

	private static final int GOA = 0;
	private static final int FIP = PRIMARY;
	private static final int SHO = 35;
	private static final int BLK = 35;
	private static final int PAS = LOW;
	private static final int TEC = MEDIUM;
	private static final int SPE = LOW;
	private static final int AGR = HIGH;

	public HandballPivotAttributeEvaluator()
	{
		super(NAME, GOA, FIP, SHO, BLK, PAS, TEC, SPE, AGR);
	}
}
