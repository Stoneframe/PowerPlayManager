package comparators;

import evaluators.AttributeEvaluator;
import model.Attributes;

public class QualityEvaluatorComparator<A extends Attributes>
	extends EvaluatorComparator<A>
{
	public QualityEvaluatorComparator(A attributes)
	{
		super(attributes);
	}

	@Override
	protected double getValue(AttributeEvaluator<A> evaluator, A attributes)
	{
		return evaluator.getQuality(attributes);
	}
}
