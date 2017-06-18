package model.comparators;

import model.Player;
import model.PlayerEvaluator;

public class RatingEvaluatorComparator extends EvaluatorComparator
{
	public RatingEvaluatorComparator(Player player)
	{
		super(player);
	}

	@Override
	protected double getValue(PlayerEvaluator evaluator, Player player)
	{
		return evaluator.getRating(player.getAttributes());
	}
}
