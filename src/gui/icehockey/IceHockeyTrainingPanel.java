package gui.icehockey;

import evaluators.PlayerEvaluator;
import gui.player.TrainingPanel;
import model.icehockey.IceHockeyAttributes;

public class IceHockeyTrainingPanel
	extends TrainingPanel<IceHockeyAttributes>
{
	private static final long serialVersionUID = 3550520789124931078L;

	public IceHockeyTrainingPanel(PlayerEvaluator<IceHockeyAttributes> playerEvaluator)
	{
		super(playerEvaluator);
	}
}
