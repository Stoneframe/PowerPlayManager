package searcher.criterias;

import evaluators.PlayerEvaluator;
import model.Attributes;
import model.Player;
import model.Side;
import searcher.SearchCriteria;

public class SideSearchCriteria<A extends Attributes>
	extends SearchCriteria<A>
{
	private final String side;

	public SideSearchCriteria(PlayerEvaluator<A> playerEvaluator, String side)
	{
		super(playerEvaluator);

		this.side = side;
	}

	private static final long serialVersionUID = -3817513251249583650L;

	@Override
	public String getName()
	{
		return "Side";
	}

	@Override
	public boolean check(Player<A> player)
	{
		Side side = Side.parse(this.side);

		return side.matches(player.getSide());
	}

	public String getSide()
	{
		return side;
	}
}
