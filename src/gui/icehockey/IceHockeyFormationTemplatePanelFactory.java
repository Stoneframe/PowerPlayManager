package gui.icehockey;

import evaluators.PlayerEvaluator;
import formation.icehockey.IceHockeyFormationTemplate;
import gui.formation.FormationTemplatePanel;
import gui.formation.FormationTemplatePanelFactory;
import model.icehockey.IceHockeyAttributes;

public class IceHockeyFormationTemplatePanelFactory
	implements
		FormationTemplatePanelFactory<IceHockeyFormationTemplate, IceHockeyAttributes>
{
	@Override
	public FormationTemplatePanel<IceHockeyFormationTemplate> newInstance(
			PlayerEvaluator<IceHockeyAttributes> playerEvaluator)
	{
		return new IceHockeyFormationTemplatePanel(playerEvaluator);
	}
}
