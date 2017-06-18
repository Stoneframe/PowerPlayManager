package gui;

import model.Player;
import model.PlayerEvaluator;
import model.comparators.QualityEvaluatorComparator;

public class TrainingSuggestionPanel extends SuggestionPanel
{
	private static final long serialVersionUID = -9177249749398783715L;

	public TrainingSuggestionPanel()
	{
		super("Traning");
	}

	@Override
	protected int compare(
			Player player,
			PlayerEvaluator evaluator1,
			PlayerEvaluator evaluator2)

	{
		return new QualityEvaluatorComparator(player)
				.compare(evaluator1, evaluator2);
	}

	@Override
	protected double getValue(Player player, PlayerEvaluator evaluator)
	{
		return evaluator.getQuality(player.getAttributes());
	}
}
