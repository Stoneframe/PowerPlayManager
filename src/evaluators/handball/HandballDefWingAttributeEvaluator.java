package evaluators.handball;

public class HandballDefWingAttributeEvaluator
	extends HandballWingAttributeEvaluator
{
	private static final String NAME = "Def. Wing";

	private static final int SHO = 0;
	private static final int BLK = 70;

	public HandballDefWingAttributeEvaluator()
	{
		super(NAME, SHO, BLK);
	}
}
