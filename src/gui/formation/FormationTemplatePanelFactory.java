package gui.formation;

import evaluators.PlayerEvaluator;
import formation.FormationTemplate;
import model.Attributes;

public interface FormationTemplatePanelFactory<
		A extends Attributes,
		FT extends FormationTemplate<A>>
{
	public FormationTemplatePanel<A, FT> newInstance(PlayerEvaluator<A> playerEvaluators);
}
