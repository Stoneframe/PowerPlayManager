package comparators;

import evaluators.PlayerEvaluator;
import model.Attributes;

public class RatingEvaluatorComparator<A extends Attributes>
		extends
		EvaluatorComparator<A>
{
	public RatingEvaluatorComparator(A attributes)
	{
		super(attributes);
	}

	@Override
	protected double getValue(
			PlayerEvaluator<A> evaluator,
			A attributes)
	{
		return evaluator.getRating(attributes);
	}
}
