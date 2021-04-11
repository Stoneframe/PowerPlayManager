package gui.icehockey;

import evaluators.PlayerEvaluator;
import gui.formation.FormationTemplatePanel;
import gui.formation.FormationTemplatePanelFactory;
import model.icehockey.IceHockeyAttributes;

public class IceHockeyFormationTemplatePanelFactory
	implements
		FormationTemplatePanelFactory<IceHockeyAttributes>
{
	@Override
	public FormationTemplatePanel<IceHockeyAttributes> newInstance(
		PlayerEvaluator<IceHockeyAttributes> playerEvaluator)
	{
		return new IceHockeyFormationTemplatePanel(playerEvaluator);
	}
}
