package comparators;

import evaluators.AttributeEvaluator;
import model.Attributes;

public class RatingEvaluatorComparator<A extends Attributes> extends EvaluatorComparator<A>
{
	public RatingEvaluatorComparator(A attributes)
	{
		super(attributes);
	}

	@Override
	protected double getValue(AttributeEvaluator<A> evaluator, A attributes)
	{
		return evaluator.getRating(attributes);
	}
}
