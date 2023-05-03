package searcher.criterias;

import evaluators.PlayerEvaluator;
import model.Attributes;
import model.Player;
import model.Side;
import searcher.SearchCriteria;

public class SideSearchCriteria<A extends Attributes>
	extends SearchCriteria<A>
{
	public static final String NAME = "Side";

	private final String side;

	public SideSearchCriteria(PlayerEvaluator<A> playerEvaluator, String side)
	{
		super(playerEvaluator);

		this.side = side;
	}

	@Override
	public String getName()
	{
		return NAME;
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
