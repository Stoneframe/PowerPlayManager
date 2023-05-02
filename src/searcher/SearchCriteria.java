package searcher;

import java.io.Serializable;

import evaluators.PlayerEvaluator;
import model.Attributes;
import model.Player;

public abstract class SearchCriteria<A extends Attributes>
	implements
		Serializable
{
	private static final long serialVersionUID = 3220332335301780225L;

	protected transient PlayerEvaluator<A> playerEvaluator;

	protected SearchCriteria(PlayerEvaluator<A> playerEvaluator)
	{
		this.playerEvaluator = playerEvaluator;
	}

	public abstract String getName();

	public abstract boolean check(Player<A> player);
}
