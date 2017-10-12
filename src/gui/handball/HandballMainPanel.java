package gui.handball;

import java.util.List;

import builders.formation.handball.HandballFormationTemplate;
import builders.formation.handball.HandballPaulsFormationBuilder;
import evaluators.AttributeEvaluator;
import evaluators.FacilityEvaluator;
import evaluators.PlayerEvaluator;
import gui.MainPanel;
import model.handball.HandballAttributes;
import model.handball.HandballFormation;
import parsers.players.PlayersParser;
import predictors.handball.HandballPlayerPredictor;

public class HandballMainPanel
	extends MainPanel<HandballAttributes, HandballFormation, HandballFormationTemplate>
{
	private static final long serialVersionUID = -3164509414943511994L;

	public HandballMainPanel(
			FacilityEvaluator facilityEvaluator,
			PlayerEvaluator<HandballAttributes> playerEvaluator,
			List<AttributeEvaluator<HandballAttributes>> attributeEvaluators,
			List<PlayersParser<HandballAttributes>> parsers)
	{
		super(
				new HandballAttributesPanel(),
				new HandballFormationTemplatePanelFactory(),
				new HandballFormationPanelFactory(),
				new HandballPaulsFormationBuilder(),
				playerEvaluator,
				attributeEvaluators,
				new HandballPlayerPredictor(playerEvaluator, attributeEvaluators),
				parsers);
	}
}
