package evaluators.handball;

public class HandballOffBackAttributeEvaluator
	extends HandballBackAttributeEvaluator
{
	private static final String NAME = "Off. Back";

	private static final int SHO = 85;
	private static final int BLK = 0;

	public HandballOffBackAttributeEvaluator()
	{
		super(NAME, SHO, BLK);
	}
}
