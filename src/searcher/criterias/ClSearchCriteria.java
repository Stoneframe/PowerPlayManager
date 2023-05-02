package searcher.criterias;

import evaluators.PlayerEvaluator;
import model.Attributes;
import model.Player;
import searcher.SearchCriteria;

public class ClSearchCriteria<A extends Attributes>
	extends SearchCriteria<A>
{
	private static final long serialVersionUID = -2496655417379568675L;

	private final int min;
	private final int max;

	public ClSearchCriteria(PlayerEvaluator<A> playerEvaluator, int min, int max)
	{
		super(playerEvaluator);

		this.min = min;
		this.max = max;
	}

	@Override
	public String getName()
	{
		return "CL";
	}

	@Override
	public boolean check(Player<A> player)
	{
		return min <= player.getCL() && player.getCL() <= max;
	}

	public int getMin()
	{
		return min;
	}

	public int getMax()
	{
		return max;
	}
}
