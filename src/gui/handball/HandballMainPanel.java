package gui.handball;

import java.util.List;

import builders.formation.handball.HandballFormationTemplate;
import builders.formation.handball.HandballPaulsFormationBuilder;
import evaluators.AttributeEvaluator;
import evaluators.handball.HandballPlayerEvaluator;
import gui.MainPanel;
import model.handball.HandballAttributes;
import model.handball.HandballFormation;
import parsers.players.PlayersParser;

public class HandballMainPanel
		extends MainPanel<HandballAttributes, HandballFormation, HandballFormationTemplate>
{
	private static final long serialVersionUID = -3164509414943511994L;

	public HandballMainPanel(
			List<AttributeEvaluator<HandballAttributes>> attributeEvaluators,
			List<PlayersParser<HandballAttributes>> parsers)
	{
		super(
				new HandballAttributesPanel(),
				new HandballFormationTemplatePanel(attributeEvaluators),
				new HandballFormationPanelFactory(),
				new HandballPaulsFormationBuilder(),
				new HandballPlayerEvaluator(attributeEvaluators),
				attributeEvaluators,
				parsers);
	}
}
