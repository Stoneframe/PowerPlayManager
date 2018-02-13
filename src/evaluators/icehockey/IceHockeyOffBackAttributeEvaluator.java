package evaluators.icehockey;

public class IceHockeyOffBackAttributeEvaluator
	extends IceHockeyAttributeEvaluator
{
	private static final String NAME = "Back (Off)";

	private static final int GOA = 0;
	private static final int DEF = 100;
	private static final int OFF = 0;
	private static final int SHO = 65;
	private static final int PAS = 45;
	private static final int TEC = 35;
	private static final int AGR = 45;

	public IceHockeyOffBackAttributeEvaluator()
	{
		super(NAME, GOA, DEF, OFF, SHO, PAS, TEC, AGR);
	}
}
