package gui.football;

import java.util.List;

import evaluators.PlayerEvaluator;
import formation.PaulsFormationBuilder;
import gui.MainPanel;
import gui.menu.FileHandler;
import gui.menu.MenuBar;
import model.football.FootballAttributes;
import parsers.players.PlayersParser;

public class FootballMainPanel
	extends MainPanel<FootballAttributes>
{
	private static final long serialVersionUID = -4461292547199081549L;

	public FootballMainPanel(
			MenuBar menuBar,
			FileHandler<FootballAttributes> fileHandler,
			List<PlayersParser<FootballAttributes>> parsers,
			PlayerEvaluator<FootballAttributes> playerEvaluator)
	{
		super(
			menuBar,
			fileHandler,
			new FootballAttributesPanel(),
			new FootballFormationTemplatePanelFactory(),
			new PaulsFormationBuilder<>(playerEvaluator),
			parsers,
			playerEvaluator);
	}
}
