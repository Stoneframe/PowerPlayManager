package model.comparators;

import model.Attributes;
import model.PlayerEvaluator;

public class QualityEvaluatorComparator extends EvaluatorComparator
{
	public QualityEvaluatorComparator(Attributes attributes)
	{
		super(attributes);
	}

	@Override
	protected double getValue(PlayerEvaluator evaluator, Attributes attributes)
	{
		return evaluator.getQuality(attributes);
	}
}
