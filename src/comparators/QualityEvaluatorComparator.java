package comparators;

import evaluators.PlayerEvaluator;
import model.Attributes;

public class QualityEvaluatorComparator<A extends Attributes>
		extends
		EvaluatorComparator<A>
{
	public QualityEvaluatorComparator(A attributes)
	{
		super(attributes);
	}

	@Override
	protected double getValue(
			PlayerEvaluator<A> evaluator,
			A attributes)
	{
		return evaluator.getQuality(attributes);
	}
}
