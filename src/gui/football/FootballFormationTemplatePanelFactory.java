package gui.football;

import evaluators.PlayerEvaluator;
import gui.formation.FormationTemplatePanel;
import gui.formation.FormationTemplatePanelFactory;
import model.football.FootballAttributes;

public class FootballFormationTemplatePanelFactory
	implements
		FormationTemplatePanelFactory<FootballAttributes>
{

	@Override
	public FormationTemplatePanel<FootballAttributes> newInstance(
			PlayerEvaluator<FootballAttributes> playerEvaluator)
	{
		return new FootballFormationTemplatePanel(playerEvaluator);
	}
}
