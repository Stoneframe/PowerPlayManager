package evaluators.handball;

public class HandballDefWingAttributeEvaluator
	extends HandballAttributeEvaluator
{
	private static final String NAME = "Def. Wing";

	private static final int GOA = 0;
	private static final int FIP = PRIMARY;
	private static final int SHO = 0;
	private static final int BLK = 70;
	private static final int PAS = LOW;
	private static final int TEC = MEDIUM;
	private static final int SPE = HIGH;
	private static final int AGR = LOW;

	public HandballDefWingAttributeEvaluator()
	{
		super(NAME, GOA, FIP, SHO, BLK, PAS, TEC, SPE, AGR);
	}
}
