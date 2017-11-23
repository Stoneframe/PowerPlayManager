package gui.handball;

import java.util.List;

import evaluators.AttributeEvaluator;
import gui.player.TrainingPanel;
import model.handball.HandballAttributes;

public class HandballTrainingPanel
	extends TrainingPanel<HandballAttributes>
{
	private static final long serialVersionUID = 1036769887458963598L;

	public HandballTrainingPanel(List<AttributeEvaluator<HandballAttributes>> attributeEvaluators)
	{
		super(attributeEvaluators);
	}
}
