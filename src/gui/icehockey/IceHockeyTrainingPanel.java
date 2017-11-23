package gui.icehockey;

import java.util.List;

import evaluators.AttributeEvaluator;
import gui.player.TrainingPanel;
import model.icehockey.IceHockeyAttributes;

public class IceHockeyTrainingPanel
	extends TrainingPanel<IceHockeyAttributes>
{
	private static final long serialVersionUID = 3550520789124931078L;

	public IceHockeyTrainingPanel(
			List<AttributeEvaluator<IceHockeyAttributes>> attributeEvaluators)
	{
		super(attributeEvaluators);
	}
}
