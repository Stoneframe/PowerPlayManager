package gui.icehockey;

import java.util.List;

import evaluators.PlayerEvaluator;
import formation.PaulsFormationBuilder;
import gui.MainPanel;
import gui.menu.FileHandler;
import gui.menu.MenuBar;
import model.icehockey.IceHockeyAttributes;
import parsers.players.PlayersParser;
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
			playerEvaluator);
	}
}
