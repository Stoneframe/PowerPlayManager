package searcher.criterias;

import evaluators.PlayerEvaluator;
import evaluators.PositionNameValue;
import model.Attributes;
import model.Player;
import searcher.SearchCriteria;

public class BestPositionSearchCriteria<A extends Attributes>
	extends SearchCriteria<A>
{
	private final String positionName;

	public BestPositionSearchCriteria(PlayerEvaluator<A> playerEvaluator, String positionName)
	{
		super(playerEvaluator);
		
		this.positionName = positionName;
	}

	private static final long serialVersionUID = 4022387670747832230L;

	@Override
	public String getName()
	{
		return null;
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
