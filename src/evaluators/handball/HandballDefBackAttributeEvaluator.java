package evaluators.handball;

public class HandballDefBackAttributeEvaluator
	extends HandballBackAttributeEvaluator
{
	private static final String NAME = "Def. Back";

	private static final int SHO = 0;
	private static final int BLK = 85;

	public HandballDefBackAttributeEvaluator()
	{
		super(NAME, SHO, BLK);
	}
}
