package gui;

import java.util.EventObject;
import java.util.List;

import model.PlayerEvaluator;

public class PlayerEvaluatorsParsedEvent extends EventObject
{
	private static final long serialVersionUID = 5087168124511401024L;

	private List<PlayerEvaluator> playerEvaluator;

	public PlayerEvaluatorsParsedEvent(
			Object source,
			List<PlayerEvaluator> playerEvaluators)
	{
		super(source);

		this.playerEvaluator = playerEvaluators;
	}

	public List<PlayerEvaluator> getPlayerEvaluators()
	{
		return playerEvaluator;
	}
}
