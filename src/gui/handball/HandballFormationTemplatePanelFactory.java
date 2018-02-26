package gui.handball;

import evaluators.PlayerEvaluator;
import formation.handball.HandballFormationTemplate;
import gui.formation.FormationTemplatePanel;
import gui.formation.FormationTemplatePanelFactory;
import model.handball.HandballAttributes;

public class HandballFormationTemplatePanelFactory
	implements
		FormationTemplatePanelFactory<HandballAttributes, HandballFormationTemplate>
{
	@Override
	public FormationTemplatePanel<HandballAttributes, HandballFormationTemplate> newInstance(
			PlayerEvaluator<HandballAttributes> playerEvaluator)
	{
		return new HandballFormationTemplatePanel(playerEvaluator);
	}
}
