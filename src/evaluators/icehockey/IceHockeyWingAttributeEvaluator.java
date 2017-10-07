package evaluators.icehockey;

public class IceHockeyWingAttributeEvaluator
	extends IceHockeyAttributeEvaluator
{
	private static final String NAME = "Wing";

	private static final int GOA = 0;
	private static final int DEF = 0;
	private static final int OFF = 100;
	private static final int SHO = 70;
	private static final int PAS = 0;
	private static final int TEC = 50;
	private static final int AGR = 50;

	public IceHockeyWingAttributeEvaluator()
	{
		super(NAME, GOA, DEF, OFF, SHO, PAS, TEC, AGR);
	}
}
