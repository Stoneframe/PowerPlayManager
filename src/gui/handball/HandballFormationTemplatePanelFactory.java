package gui.handball;

import evaluators.PlayerEvaluator;
import gui.formation.FormationTemplatePanel;
import gui.formation.FormationTemplatePanelFactory;
import model.handball.HandballAttributes;

public class HandballFormationTemplatePanelFactory
	implements
		FormationTemplatePanelFactory<HandballAttributes>
{
	@Override
	public FormationTemplatePanel<HandballAttributes> newInstance(
		PlayerEvaluator<HandballAttributes> playerEvaluator)
	{
		return new HandballFormationTemplatePanel(playerEvaluator);
	}
}
