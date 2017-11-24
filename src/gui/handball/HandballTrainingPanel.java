package gui.handball;

import evaluators.PlayerEvaluator;
import gui.player.TrainingPanel;
import model.handball.HandballAttributes;

public class HandballTrainingPanel
	extends TrainingPanel<HandballAttributes>
{
	private static final long serialVersionUID = 1036769887458963598L;

	public HandballTrainingPanel(PlayerEvaluator<HandballAttributes> playerEvaluator)
	{
		super(playerEvaluator);
	}
}
