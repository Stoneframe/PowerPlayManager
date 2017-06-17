package gui;

import model.Player;
import model.PlayerEvaluator;

public class PositionSuggestionPanel extends SuggestionPanel
{
	private static final long serialVersionUID = -1128283837238130849L;

	public PositionSuggestionPanel()
	{
		super("Position");
	}

	@Override
	protected double getValue(Player player, PlayerEvaluator evaluator)
	{
		return evaluator.getRating(player.getAttributes());
	}
}
