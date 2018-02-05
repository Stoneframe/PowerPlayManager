package evaluators.handball;

public class HandballOffPivotAttributeEvaluator
	extends HandballAttributeEvaluator
{
	private static final String NAME = "Off. Pivot";

	private static final int GOA = 0;
	private static final int FIP = PRIMARY;
	private static final int SHO = 70;
	private static final int BLK = 0;
	private static final int PAS = LOW;
	private static final int TEC = MEDIUM;
	private static final int SPE = LOW;
	private static final int AGR = HIGH;

	public HandballOffPivotAttributeEvaluator()
	{
		super(NAME, GOA, FIP, SHO, BLK, PAS, TEC, SPE, AGR);
	}
}
