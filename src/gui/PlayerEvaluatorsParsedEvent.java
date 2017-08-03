package gui;

import java.util.EventObject;
import java.util.List;

import evaluators.PlayerEvaluator;
import model.Attributes;

public class PlayerEvaluatorsParsedEvent extends EventObject
{
	private static final long serialVersionUID = 5087168124511401024L;

	private List<PlayerEvaluator<Attributes>> playerEvaluator;

	public PlayerEvaluatorsParsedEvent(
			Object source,
			List<PlayerEvaluator<Attributes>> playerEvaluators)
	{
		super(source);

		this.playerEvaluator = playerEvaluators;
	}

	public List<PlayerEvaluator<Attributes>> getPlayerEvaluators()
	{
		return playerEvaluator;
	}
}
