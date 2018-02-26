package gui.icehockey;

import java.util.List;

import evaluators.PlayerEvaluator;
import formation.icehockey.IceHockeyFormationTemplate;
import formation.icehockey.IceHockeyPaulsFormationBuilder;
import gui.MainPanel;
import model.icehockey.IceHockeyAttributes;
import model.icehockey.IceHockeyFormation;
import parsers.players.PlayersParser;

public class IceHockeyMainPanel
	extends MainPanel<IceHockeyAttributes, IceHockeyFormation, IceHockeyFormationTemplate>
{
	private static final long serialVersionUID = -9170227741926378853L;

	public IceHockeyMainPanel(
			List<PlayersParser<IceHockeyAttributes>> parsers,
			PlayerEvaluator<IceHockeyAttributes> playerEvaluator)
	{
		super(
			new IceHockeyAttributesPanel(),
			new IceHockeyTrainingPanel(playerEvaluator),
			new IceHockeyFormationTemplatePanelFactory(),
			new IceHockeyFormationPanelFactory(),
			new IceHockeyPaulsFormationBuilder(),
			parsers,
			playerEvaluator);
	}
}
