package evaluators.icehockey;

public class IceHockeyDefBackAttributeEvaluator
	extends IceHockeyAttributeEvaluator
{
	private static final String NAME = "Back (Def)";

	private static final int GOA = 0;
	private static final int DEF = 100;
	private static final int OFF = 0;
	private static final int SHO = 20;
	private static final int PAS = 50;
	private static final int TEC = 40;
	private static final int AGR = 50;

	public IceHockeyDefBackAttributeEvaluator()
	{
		super(NAME, GOA, DEF, OFF, SHO, PAS, TEC, AGR);
	}
}
