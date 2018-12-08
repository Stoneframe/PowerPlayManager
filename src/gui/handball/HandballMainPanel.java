package gui.handball;

import java.util.List;

import evaluators.PlayerEvaluator;
import formation.PaulsFormationBuilder;
import gui.MainPanel;
import gui.menu.FileHandler;
import gui.menu.MenuBar;
import model.handball.HandballAttributes;
import parsers.players.PlayersParser;
import warper.handball.HandballPlayerWarper;

public class HandballMainPanel
	extends MainPanel<HandballAttributes>
{
	private static final long serialVersionUID = -3164509414943511994L;

	public HandballMainPanel(
			MenuBar menuBar,
			FileHandler<HandballAttributes> fileHandler,
			List<PlayersParser<HandballAttributes>> parsers,
			PlayerEvaluator<HandballAttributes> playerEvaluator)
	{
		super(
			menuBar,
			fileHandler,
			new HandballAttributesPanel(),
			new HandballFormationTemplatePanelFactory(),
			new PaulsFormationBuilder<>(),
			new HandballPlayerWarper(playerEvaluator),
			parsers,
			playerEvaluator);
	}
}
