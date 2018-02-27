package gui.formation;

import evaluators.PlayerEvaluator;
import model.Attributes;

public interface FormationTemplatePanelFactory<A extends Attributes>
{
	public FormationTemplatePanel<A> newInstance(PlayerEvaluator<A> playerEvaluators);
}
