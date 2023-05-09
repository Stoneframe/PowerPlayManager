package searcher.criterias;

import evaluators.PlayerEvaluator;
import evaluators.PositionNameValue;
import model.Attributes;
import model.Player;
import searcher.SearchCriteria;

public class BestPositionRatingSearchCriteria<A extends Attributes>
	extends SearchCriteria<A>
{
	public static final String NAME = "Best Position (Rating)";

	private final String positionName;

	public BestPositionRatingSearchCriteria(
		PlayerEvaluator<A> playerEvaluator,
		String positionName)
	{
		super(playerEvaluator);

		this.positionName = positionName;
	}

	@Override
	public String getName()
	{
		return NAME;
	}

	@Override
	public boolean check(Player<A> player)
	{
		PositionNameValue position = playerEvaluator.getBestPositionRating(player);

		return getPositionName().equals(position.getName());
	}

	public String getPositionName()
	{
		return positionName;
	}
}
