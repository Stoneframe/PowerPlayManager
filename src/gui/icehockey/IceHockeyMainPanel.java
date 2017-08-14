package gui.icehockey;

import java.util.List;

import builders.formation.icehockey.IceHockeyFormationTemplate;
import builders.formation.icehockey.PaulsIceHockeyFormationBuilder;
import evaluators.PlayerEvaluator;
import gui.MainPanel;
import model.icehockey.IceHockeyAttributes;
import model.icehockey.IceHockeyFormation;
import parsers.players.PlayersParser;

public class IceHockeyMainPanel
		extends MainPanel<IceHockeyAttributes, IceHockeyFormation, IceHockeyFormationTemplate>
{
	private static final long serialVersionUID = -9170227741926378853L;

	public IceHockeyMainPanel(
			List<PlayerEvaluator<IceHockeyAttributes>> evaluators,
			List<PlayersParser<IceHockeyAttributes>> parsers)
	{
		super(
				new IceHockeyAttributesPanel(),
				new IceHockeyFormationTemplatePanel(evaluators),
				new IceHockeyFormationPanelFactory(),
				new PaulsIceHockeyFormationBuilder(),
				evaluators,
				parsers);
	}
}
