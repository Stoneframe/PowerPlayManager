package gui.football;

import evaluators.PlayerEvaluator;
import gui.player.TrainingPlannerPanel;
import model.football.FootballAttributes;

public class FootballTrainingPanel
	extends TrainingPlannerPanel<FootballAttributes>
{
	private static final long serialVersionUID = -6527645056843254528L;

	protected FootballTrainingPanel(PlayerEvaluator<FootballAttributes> playerEvaluator)
	{
		super(playerEvaluator);
	}
}
