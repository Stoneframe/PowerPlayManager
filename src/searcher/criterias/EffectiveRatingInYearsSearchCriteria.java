package searcher.criterias;

import evaluators.AttributeEvaluator;
import evaluators.PlayerEvaluator;
import model.Attributes;
import model.Player;
import searcher.SearchCriteria;
import warper.PlayerWarper;

public class EffectiveRatingInYearsSearchCriteria<A extends Attributes>
	extends SearchCriteria<A>
{
	public static final String NAME = "Effective Rating in Years";

	private final int min;
	private final int max;
	private final int years;

	private transient final PlayerWarper<A> playerWarper;

	public EffectiveRatingInYearsSearchCriteria(
		int min,
		int max,
		int years,
		PlayerEvaluator<A> playerEvaluator,
		PlayerWarper<A> playerWarper)
	{
		super(playerEvaluator);

		this.min = min;
		this.max = max;
		this.years = years;
		this.playerWarper = playerWarper;
	}

	@Override
	public String getName()
	{
		return NAME;
	}

	@Override
	public boolean check(Player<A> player)
	{
		AttributeEvaluator<A> attributeEvaluator =
			playerEvaluator.getBestEvaluatorByRating(player.getAttributes());

		A attributes = playerWarper.warp(player, attributeEvaluator, years);

		double rating = attributeEvaluator.getRating(attributes);

		return min <= rating && rating <= max;
	}

	public int getMin()
	{
		return min;
	}

	public int getMax()
	{
		return max;
	}

	public int getYears()
	{
		return years;
	}
}
