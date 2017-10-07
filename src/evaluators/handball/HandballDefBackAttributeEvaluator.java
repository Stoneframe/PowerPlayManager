package evaluators.handball;

public class HandballDefBackAttributeEvaluator
	extends HandballAttributeEvaluator
{
	private static final String NAME = "Def. Back";

	private static final int GOA = 0;
	private static final int FIP = 100;
	private static final int SHO = 0;
	private static final int BLK = 70;
	private static final int PAS = 70;
	private static final int TEC = 50;
	private static final int SPE = 30;
	private static final int AGR = 30;

	public HandballDefBackAttributeEvaluator()
	{
		super(NAME, GOA, FIP, SHO, BLK, PAS, TEC, SPE, AGR);
	}
}
