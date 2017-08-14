package evaluators.icehockey;

public class IceHockeySpecialPlayerEvaluator extends IceHockeyPlayerEvaluator
{
	private static final String NAME = "Special";

	private static final int GOA = 0;
	private static final int DEF = 0;
	private static final int OFF = 100;
	private static final int SHO = 70;
	private static final int PAS = 25;
	private static final int TEC = 50;
	private static final int AGR = 25;

	public IceHockeySpecialPlayerEvaluator()
	{
		super(NAME, GOA, DEF, OFF, SHO, PAS, TEC, AGR);
	}
}
