package model.comparators;

import model.Attributes;
import model.PlayerEvaluator;

public class RatingEvaluatorComparator extends EvaluatorComparator
{
	public RatingEvaluatorComparator(Attributes attributes)
	{
		super(attributes);
	}

	@Override
	protected double getValue(PlayerEvaluator evaluator, Attributes attributes)
	{
		return evaluator.getRating(attributes);
	}
}
