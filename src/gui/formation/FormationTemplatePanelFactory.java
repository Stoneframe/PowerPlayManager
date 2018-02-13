package gui.formation;

import evaluators.PlayerEvaluator;
import formation.FormationTemplate;
import model.Attributes;

public interface FormationTemplatePanelFactory<FT extends FormationTemplate, A extends Attributes>
{
	public FormationTemplatePanel<FT> newInstance(PlayerEvaluator<A> playerEvaluators);
}
