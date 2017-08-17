package evaluators.icehockey;

public class IceHockeyGoalieAttributeEvaluator extends IceHockeyAttributeEvaluator
{
	private static final String NAME = "Goalie";

	private static final int GOA = 100;
	private static final int DEF = 0;
	private static final int OFF = 0;
	private static final int SHO = 0;
	private static final int PAS = 50;
	private static final int TEC = 50;
	private static final int AGR = 0;

	public IceHockeyGoalieAttributeEvaluator()
	{
		super(NAME, GOA, DEF, OFF, SHO, PAS, TEC, AGR);
	}
}
