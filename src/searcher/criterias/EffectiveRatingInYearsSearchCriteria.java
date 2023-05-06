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

	private final String positionName;
	private final int min;
	private final int max;
	private final int years;

	private transient final PlayerWarper<A> playerWarper;

	public EffectiveRatingInYearsSearchCriteria(
		String positionName,
		int min,
		int max,
		int years,
		PlayerEvaluator<A> playerEvaluator,
		PlayerWarper<A> playerWarper)
	{
		super(playerEvaluator);

		this.positionName = positionName;
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
		AttributeEvaluator<A> attributeEvaluator = playerEvaluator.getAttributeEvaluators(true)
			.stream()
			.filter(e -> e.getName().equals(positionName))
			.findFirst()
			.get();

		A attributes = playerWarper.warp(player, attributeEvaluator, years);

		double rating = attributeEvaluator.getRating(attributes);

		return min <= rating && rating <= max;
	}

	public String getPositionName()
	{
		return positionName;
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
