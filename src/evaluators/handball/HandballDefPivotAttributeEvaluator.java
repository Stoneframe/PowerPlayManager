package evaluators.handball;

public class HandballDefPivotAttributeEvaluator
	extends HandballPivotAttributeEvaluator
{
	private static final String NAME = "Def. Pivot";

	private static final int SHO = 0;
	private static final int BLK = 70;

	public HandballDefPivotAttributeEvaluator()
	{
		super(NAME, SHO, BLK);
	}
}
