package gui.handball;

import java.util.List;

import evaluators.PlayerEvaluator;
import files.FileHandler;
import formation.PaulsFormationBuilder;
import gui.MainPanel;
import gui.menu.MenuBar;
import importer.handball.HandballImporter;
import model.handball.HandballAttributes;
import parsers.players.PlayersParser;
import searcher.SearchTemplateStorage;
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
			playerEvaluator,
			new SearchTemplateStorage<HandballAttributes>(
				"search-handball",
				playerEvaluator,
				new HandballPlayerWarper(playerEvaluator)),
			new HandballImporter(fileHandler));
	}
}
