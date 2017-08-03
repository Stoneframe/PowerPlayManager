package evaluators.handball;

public class HandballOffPivotPlayerEvaluator extends HandballPlayerEvaluator
{
	private static final String NAME = "Off. Pivot";

	private static final int GOA = 0;
	private static final int FIP = 100;
	private static final int SHO = 80;
	private static final int BLK = 40;
	private static final int PAS = 40;
	private static final int TEC = 60;
	private static final int SPE = 40;
	private static final int AGR = 80;

	public HandballOffPivotPlayerEvaluator()
	{
		super(NAME, GOA, FIP, SHO, BLK, PAS, TEC, SPE, AGR);
	}
}
