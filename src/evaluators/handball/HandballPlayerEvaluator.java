package evaluators.handball;

import java.util.List;

import evaluators.AttributeEvaluator;
import evaluators.PlayerEvaluator;
import model.handball.HandballAttributes;

public class HandballPlayerEvaluator extends PlayerEvaluator<HandballAttributes>
{
	private static final double A = -0.118, B = 5.1, C = 36.8, D = -1079;

	public HandballPlayerEvaluator(List<AttributeEvaluator<HandballAttributes>> attributeEvaluators)
	{
		super(attributeEvaluators, A, B, C, D);
	}
}
