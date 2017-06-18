package model.comparators;

import model.Player;
import model.PlayerEvaluator;

public class QualityEvaluatorComparator extends EvaluatorComparator
{
	public QualityEvaluatorComparator(Player player)
	{
		super(player);
	}

	@Override
	protected double getValue(PlayerEvaluator evaluator, Player player)
	{
		return evaluator.getQuality(player.getAttributes());
	}
}
