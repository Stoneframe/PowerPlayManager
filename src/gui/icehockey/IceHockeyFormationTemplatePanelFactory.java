package gui.icehockey;

import java.util.List;

import builders.formation.icehockey.IceHockeyFormationTemplate;
import evaluators.AttributeEvaluator;
import gui.formation.FormationTemplatePanel;
import gui.formation.FormationTemplatePanelFactory;
import model.icehockey.IceHockeyAttributes;

public class IceHockeyFormationTemplatePanelFactory
	implements
		FormationTemplatePanelFactory<IceHockeyFormationTemplate, IceHockeyAttributes>
{
	@Override
	public FormationTemplatePanel<IceHockeyFormationTemplate> newInstance(
			List<AttributeEvaluator<IceHockeyAttributes>> attributeEvaluators)
	{
		return new IceHockeyFormationTemplatePanel(attributeEvaluators);
	}
}
