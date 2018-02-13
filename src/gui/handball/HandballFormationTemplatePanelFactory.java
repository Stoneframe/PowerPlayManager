package gui.handball;

import evaluators.PlayerEvaluator;
import formation.handball.HandballFormationTemplate;
import gui.formation.FormationTemplatePanel;
import gui.formation.FormationTemplatePanelFactory;
import model.handball.HandballAttributes;

public class HandballFormationTemplatePanelFactory
	implements
		FormationTemplatePanelFactory<HandballFormationTemplate, HandballAttributes>
{
	@Override
	public FormationTemplatePanel<HandballFormationTemplate> newInstance(
			PlayerEvaluator<HandballAttributes> playerEvaluator)
	{
		return new HandballFormationTemplatePanel(playerEvaluator);
	}
}
