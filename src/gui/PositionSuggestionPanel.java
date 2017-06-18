package gui;

import model.Player;
import model.PlayerEvaluator;
import model.comparators.RatingEvaluatorComparator;

public class PositionSuggestionPanel extends SuggestionPanel
{
	private static final long serialVersionUID = -1128283837238130849L;

	public PositionSuggestionPanel()
	{
		super("Position");
	}

	@Override
	protected int compare(
			Player player,
			PlayerEvaluator evaluator1,
			PlayerEvaluator evaluator2)
	{
		return new RatingEvaluatorComparator(player)
				.compare(evaluator1, evaluator2);
	}

	@Override
	protected double getValue(Player player, PlayerEvaluator evaluator)
	{
		return evaluator.getRating(player.getAttributes());
	}
}
