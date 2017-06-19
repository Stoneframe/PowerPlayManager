package model.comparators;

import java.util.Comparator;

import model.Attributes;
import model.PlayerEvaluator;

public abstract class EvaluatorComparator implements Comparator<PlayerEvaluator>
{
	private Attributes attributes;

	public EvaluatorComparator(Attributes attributes)
	{
		this.attributes = attributes;
	}

	@Override
	public int compare(PlayerEvaluator evaluator1, PlayerEvaluator evaluator2)
	{
		return Double.compare(
			getValue(evaluator1, attributes),
			getValue(evaluator2, attributes));
	}

	protected abstract double getValue(
			PlayerEvaluator evaluator,
			Attributes attributes);
}
