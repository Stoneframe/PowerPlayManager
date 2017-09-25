package comparators;

import java.util.Comparator;

import evaluators.AttributeEvaluator;
import model.Attributes;

public abstract class EvaluatorComparator<A extends Attributes>
	implements
		Comparator<AttributeEvaluator<A>>
{
	private A attributes;

	public EvaluatorComparator(A attributes)
	{
		this.attributes = attributes;
	}

	@Override
	public int compare(
			AttributeEvaluator<A> evaluator1,
			AttributeEvaluator<A> evaluator2)
	{
		return Double.compare(
			getValue(evaluator1, attributes),
			getValue(evaluator2, attributes));
	}

	protected abstract double getValue(
			AttributeEvaluator<A> evaluator,
			A attributes);
}
