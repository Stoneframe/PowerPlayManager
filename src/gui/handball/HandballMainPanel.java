package gui.handball;

import java.util.List;

import calculators.handball.HandballImprovementCalculator;
import evaluators.PlayerEvaluator;
import formation.PaulsFormationBuilder;
import gui.MainPanel;
import model.handball.HandballAttributes;
import parsers.players.PlayersParser;

public class HandballMainPanel
	extends MainPanel<HandballAttributes>
{
	private static final long serialVersionUID = -3164509414943511994L;

	public HandballMainPanel(
			List<PlayersParser<HandballAttributes>> parsers,
			PlayerEvaluator<HandballAttributes> playerEvaluator)
	{
		super(
			new HandballAttributesPanel(),
			new HandballFormationTemplatePanelFactory(),
			new HandballImprovementCalculator(),
			new PaulsFormationBuilder<>(),
			parsers,
			playerEvaluator);
	}
}
