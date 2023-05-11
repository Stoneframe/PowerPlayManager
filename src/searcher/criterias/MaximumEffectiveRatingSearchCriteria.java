package searcher.criterias;

import evaluators.AttributeEvaluator;
import evaluators.PlayerEvaluator;
import model.Attributes;
import model.Player;
import searcher.SearchCriteria;
import warper.PlayerWarper;

public class MaximumEffectiveRatingSearchCriteria<A extends Attributes>
	extends SearchCriteria<A>
{
	public static final String NAME = "Maximum Effective Rating";

	private transient final PlayerWarper<A> playerWarper;

	private final int maximumRating;

	public MaximumEffectiveRatingSearchCriteria(
		PlayerEvaluator<A> playerEvaluator,
		PlayerWarper<A> playerWarper,
		int maximumRating)
	{
		super(playerEvaluator);

		this.playerWarper = playerWarper;

		this.maximumRating = maximumRating;
	}

	@Override
	public String getName()
	{
		return NAME;
	}

	@Override
	public boolean check(Player<A> player)
	{
		double highestRating = getPlayersHighestEffectiveRating(player);

		return highestRating <= maximumRating;
	}

	private double getPlayersHighestEffectiveRating(Player<A> player)
	{
		AttributeEvaluator<A> attributeEvaluator =
			playerEvaluator.getBestEvaluatorByRating(player.getAttributes());

		double highestRating = 0;

		for (int years = 0; years < 20; years++)
		{
			A attributes = playerWarper.warp(player, attributeEvaluator, years);

			double rating = attributeEvaluator.getRating(attributes);

			if (rating > highestRating)
			{
				highestRating = rating;
			}
		}

		return highestRating;
	}

	public int getMaximumRating()
	{
		return maximumRating;
	}
}
