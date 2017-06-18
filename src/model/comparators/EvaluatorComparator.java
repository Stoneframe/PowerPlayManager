package model.comparators;

import java.util.Comparator;

import model.Player;
import model.PlayerEvaluator;

public abstract class EvaluatorComparator implements Comparator<PlayerEvaluator>
{
	private Player player;

	public EvaluatorComparator(Player player)
	{
		this.player = player;
	}

	@Override
	public int compare(PlayerEvaluator evaluator1, PlayerEvaluator evaluator2)
	{
		return Double.compare(
			getValue(evaluator1, player),
			getValue(evaluator2, player));
	}

	protected abstract double getValue(
			PlayerEvaluator evaluator,
			Player player);
}
