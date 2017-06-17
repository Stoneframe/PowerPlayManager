package gui;

import model.Player;
import model.PlayerEvaluator;

public class TrainingSuggestionPanel extends SuggestionPanel
{
	private static final long serialVersionUID = -9177249749398783715L;

	public TrainingSuggestionPanel()
	{
		super("Traning");
	}

	@Override
	protected double getValue(Player player, PlayerEvaluator evaluator)
	{
		return evaluator.getQuality(player.getAttributes());
	}
}
