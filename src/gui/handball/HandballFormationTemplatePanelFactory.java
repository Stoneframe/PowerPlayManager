package gui.handball;

import java.util.List;

import evaluators.AttributeEvaluator;
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
			List<AttributeEvaluator<HandballAttributes>> attributeEvaluators)
	{
		return new HandballFormationTemplatePanel(attributeEvaluators);
	}
}
