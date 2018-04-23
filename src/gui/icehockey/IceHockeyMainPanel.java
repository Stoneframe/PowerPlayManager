package gui.icehockey;

import java.util.List;

import calculators.icehockey.IceHockeyImprovementCalculator;
import evaluators.PlayerEvaluator;
import formation.PaulsFormationBuilder;
import gui.MainPanel;
import model.icehockey.IceHockeyAttributes;
import parsers.players.PlayersParser;

public class IceHockeyMainPanel
	extends MainPanel<IceHockeyAttributes>
{
	private static final long serialVersionUID = -9170227741926378853L;

	public IceHockeyMainPanel(
			List<PlayersParser<IceHockeyAttributes>> parsers,
			PlayerEvaluator<IceHockeyAttributes> playerEvaluator)
	{
		super(
			new IceHockeyAttributesPanel(),
			new IceHockeyFormationTemplatePanelFactory(),
			new IceHockeyImprovementCalculator(),
			new PaulsFormationBuilder<>(),
			parsers,
			playerEvaluator);
	}
}
