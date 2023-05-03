package searcher.criterias;

import evaluators.PlayerEvaluator;
import model.Attributes;
import model.Player;
import searcher.SearchCriteria;

public class AgeSearchCriteria<A extends Attributes>
	extends SearchCriteria<A>
{
	public static final String NAME = "Age";

	private final int min;
	private final int max;

	public AgeSearchCriteria(PlayerEvaluator<A> playerEvaluator, int min, int max)
	{
		super(playerEvaluator);

		this.min = min;
		this.max = max;
	}

	@Override
	public String getName()
	{
		return NAME;
	}

	@Override
	public boolean check(Player<A> player)
	{
		return min <= player.getAge() && player.getAge() <= max;
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
