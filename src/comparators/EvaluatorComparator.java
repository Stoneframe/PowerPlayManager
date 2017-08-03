package comparators;

import java.util.Comparator;

import evaluators.PlayerEvaluator;
import model.Attributes;

public abstract class EvaluatorComparator<A extends Attributes>
		implements
		Comparator<PlayerEvaluator<A>>
{
	private A attributes;

	public EvaluatorComparator(A attributes)
	{
		this.attributes = attributes;
	}

	@Override
	public int compare(
			PlayerEvaluator<A> evaluator1,
			PlayerEvaluator<A> evaluator2)
	{
		return Double.compare(
			getValue(evaluator1, attributes),
			getValue(evaluator2, attributes));
	}

	protected abstract double getValue(
			PlayerEvaluator<A> evaluator,
			A attributes);
}
