package gui.icehockey;

import java.util.List;

import evaluators.PlayerEvaluator;
import files.FileHandler;
import formation.PaulsFormationBuilder;
import gui.MainPanel;
import gui.menu.MenuBar;
import importer.icehockey.IceHockeyImporter;
import model.icehockey.IceHockeyAttributes;
import parsers.players.PlayersParser;
import searcher.SearchTemplateStorage;
import warper.icehockey.IceHockeyPlayerWarper;

public class IceHockeyMainPanel
	extends MainPanel<IceHockeyAttributes>
{
	private static final long serialVersionUID = -9170227741926378853L;

	public IceHockeyMainPanel(
		MenuBar menuBar,
		FileHandler<IceHockeyAttributes> fileHandler,
		List<PlayersParser<IceHockeyAttributes>> parsers,
		PlayerEvaluator<IceHockeyAttributes> playerEvaluator)
	{
		super(
			menuBar,
			fileHandler,
			new IceHockeyAttributesPanel(),
			new IceHockeyFormationTemplatePanelFactory(),
			new PaulsFormationBuilder<>(),
			new IceHockeyPlayerWarper(playerEvaluator),
			parsers,
			playerEvaluator,
			new SearchTemplateStorage<IceHockeyAttributes>(
				"search-icehockey",
				playerEvaluator,
				new IceHockeyPlayerWarper(playerEvaluator)),
			new IceHockeyImporter(fileHandler));
	}
}
