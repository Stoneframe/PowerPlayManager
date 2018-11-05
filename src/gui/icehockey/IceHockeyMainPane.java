package gui.icehockey;

import java.util.List;

import evaluators.PlayerEvaluator;
import gui.MainPane;
import model.icehockey.IceHockeyAttributes;
import parsers.players.PlayersParser;

public class IceHockeyMainPane
	extends MainPane<IceHockeyAttributes>
{
	public IceHockeyMainPane(
			PlayerEvaluator<IceHockeyAttributes> playerEvaluator,
			List<PlayersParser<IceHockeyAttributes>> playersParsers)
	{
		super(new IceHockeyAttributesPane(), playerEvaluator, playersParsers);
	}
}
