package gui.handball;

import java.util.List;

import evaluators.AttributeEvaluator;
import evaluators.FacilityEvaluator;
import evaluators.handball.HandballPlayerEvaluator;
import formation.handball.HandballFormationTemplate;
import formation.handball.HandballPaulsFormationBuilder;
import gui.MainPanel;
import model.handball.HandballAttributes;
import model.handball.HandballFormation;
import parsers.players.PlayersParser;

public class HandballMainPanel
	extends MainPanel<HandballAttributes, HandballFormation, HandballFormationTemplate>
{
	private static final long serialVersionUID = -3164509414943511994L;

	public HandballMainPanel(
			FacilityEvaluator facilityEvaluator,
			List<AttributeEvaluator<HandballAttributes>> attributeEvaluators,
			List<PlayersParser<HandballAttributes>> parsers)
	{
		super(
				new HandballAttributesPanel(),
				new HandballFormationTemplatePanelFactory(),
				new HandballFormationPanelFactory(),
				new HandballPaulsFormationBuilder(),
				new HandballPlayerEvaluator(attributeEvaluators, facilityEvaluator),
				attributeEvaluators,
				parsers);
	}
}
