package evaluators.handball;

public class HandballOffWingAttributeEvaluator
	extends HandballWingAttributeEvaluator
{
	private static final String NAME = "Off. Wing";

	private static final int SHO = 70;
	private static final int BLK = 0;

	public HandballOffWingAttributeEvaluator()
	{
		super(NAME, SHO, BLK);
	}
}
