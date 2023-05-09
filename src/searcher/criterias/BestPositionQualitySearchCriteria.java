package searcher.criterias;

import evaluators.PlayerEvaluator;
import evaluators.PositionNameValue;
import model.Attributes;
import model.Player;
import searcher.SearchCriteria;

public class BestPositionQualitySearchCriteria<A extends Attributes>
	extends SearchCriteria<A>
{
	public static final String NAME = "Best Position (Quality)";

	private final String positionName;

	public BestPositionQualitySearchCriteria(
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
		PositionNameValue position = playerEvaluator.getBestPositionQuality(player);

		return getPositionName().equals(position.getName());
	}

	public String getPositionName()
	{
		return positionName;
	}
}
