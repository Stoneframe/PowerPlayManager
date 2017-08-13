package gui.handball;

import java.util.List;

import builders.handball.HandballFormationTemplate;
import builders.handball.PaulsHandballFormationBuilder;
import evaluators.PlayerEvaluator;
import gui.MainPanel;
import model.handball.HandballAttributes;
import model.handball.HandballFormation;
import parsers.players.PlayersParser;

public class HandballMainPanel
		extends MainPanel<HandballAttributes, HandballFormation, HandballFormationTemplate>
{
	private static final long serialVersionUID = -3164509414943511994L;

	public HandballMainPanel(
			List<PlayerEvaluator<HandballAttributes>> evaluators,
			List<PlayersParser<HandballAttributes>> parsers)
	{
		super(
				new HandballAttributesPanel(),
				new HandballFormationTemplatePanel(evaluators),
				new HandballFormationPanelFactory(),
				new PaulsHandballFormationBuilder(),
				evaluators,
				parsers);
	}
}
