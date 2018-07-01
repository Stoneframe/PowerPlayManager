package gui.football;

import java.util.List;

import evaluators.PlayerEvaluator;
import formation.PaulsFormationBuilder;
import gui.MainPanel;
import model.football.FootballAttributes;
import parsers.players.PlayersParser;

public class FootballMainPanel
	extends MainPanel<FootballAttributes>
{
	private static final long serialVersionUID = -4461292547199081549L;

	public FootballMainPanel(
			List<PlayersParser<FootballAttributes>> parsers,
			PlayerEvaluator<FootballAttributes> playerEvaluator)
	{
		super(
			new FootballAttributesPanel(),
			new FootballFormationTemplatePanelFactory(),
			new PaulsFormationBuilder<>(),
			parsers,
			playerEvaluator);
	}
}
