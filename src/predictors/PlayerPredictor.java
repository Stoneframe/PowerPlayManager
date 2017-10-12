package predictors;

import java.util.List;

import evaluators.AttributeEvaluator;
import evaluators.PlayerEvaluator;
import model.Attributes;
import model.Player;

public abstract class PlayerPredictor<A extends Attributes>
{
	protected PlayerEvaluator<A> playerEvaluator;
	protected List<AttributeEvaluator<A>> attributeEvaluators;

	public PlayerPredictor(
			PlayerEvaluator<A> playerEvaluator,
			List<AttributeEvaluator<A>> attributeEvaluators)
	{
		this.playerEvaluator = playerEvaluator;
		this.attributeEvaluators = attributeEvaluators;
	}

	public abstract void predictPlayerAttributes(Player<A> player, int yearsIntoFuture);
}
