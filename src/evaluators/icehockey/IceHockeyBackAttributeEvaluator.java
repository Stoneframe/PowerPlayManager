package evaluators.icehockey;

public class IceHockeyBackAttributeEvaluator extends IceHockeyAttributeEvaluator
{
	private static final String NAME = "Back";

	private static final int GOA = 0;
	private static final int DEF = 100;
	private static final int OFF = 0;
	private static final int SHO = 20;
	private static final int PAS = 50;
	private static final int TEC = 40;
	private static final int AGR = 50;

	public IceHockeyBackAttributeEvaluator()
	{
		super(NAME, GOA, DEF, OFF, SHO, PAS, TEC, AGR);
	}
}
