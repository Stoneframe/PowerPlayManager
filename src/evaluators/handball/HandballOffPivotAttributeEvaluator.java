package evaluators.handball;

public class HandballOffPivotAttributeEvaluator
	extends HandballPivotAttributeEvaluator
{
	private static final String NAME = "Off. Pivot";

	private static final int SHO = 70;
	private static final int BLK = 0;

	public HandballOffPivotAttributeEvaluator()
	{
		super(NAME, SHO, BLK);
	}
}
