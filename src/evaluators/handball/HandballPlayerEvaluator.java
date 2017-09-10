package evaluators.handball;

import evaluators.PlayerEvaluator;
import model.handball.HandballAttributes;

public class HandballPlayerEvaluator extends PlayerEvaluator<HandballAttributes>
{
	private static final double A = -0.001801, B = 0.01567, C = 1.351;
	private static final double AGE_15_RATING = 190;

	public HandballPlayerEvaluator()
	{
		super(A, B, C, AGE_15_RATING);
	}
}
