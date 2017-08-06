package gui;

import java.util.EventObject;
import java.util.List;

import evaluators.PlayerEvaluator;
import model.Attributes;

public class PlayerEvaluatorsParsedEvent<A extends Attributes>
		extends EventObject
{
	private static final long serialVersionUID = 5087168124511401024L;

	private List<PlayerEvaluator<A>> playerEvaluator;

	public PlayerEvaluatorsParsedEvent(
			Object source,
			List<PlayerEvaluator<A>> playerEvaluators)
	{
		super(source);

		this.playerEvaluator = playerEvaluators;
	}

	public List<PlayerEvaluator<A>> getPlayerEvaluators()
	{
		return playerEvaluator;
	}
}
