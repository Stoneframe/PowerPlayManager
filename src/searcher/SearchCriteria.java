package searcher;

import evaluators.PlayerEvaluator;
import model.Attributes;
import model.Player;

public abstract class SearchCriteria<A extends Attributes>
{
	protected transient PlayerEvaluator<A> playerEvaluator;

	protected SearchCriteria(PlayerEvaluator<A> playerEvaluator)
	{
		this.playerEvaluator = playerEvaluator;
	}

	public abstract String getName();

	public abstract boolean check(Player<A> player);
}
