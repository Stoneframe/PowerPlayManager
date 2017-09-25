package gui.formation;

import java.util.List;

import builders.formation.FormationTemplate;
import evaluators.AttributeEvaluator;
import model.Attributes;

public interface FormationTemplatePanelFactory<FT extends FormationTemplate, A extends Attributes>
{
	public FormationTemplatePanel<FT> newInstance(List<AttributeEvaluator<A>> attributeEvaluators);
}
