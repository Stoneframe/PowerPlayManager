package formation;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import evaluators.AttributeEvaluator;
import model.Attributes;
import model.Player;
import model.Side;

public class PlayerComparator<A extends Attributes>
	implements
		Comparator<Player<A>>
{
	private Map<Player<A>, Double> playerRatingCache = new HashMap<>();

	private Side side;
	private AttributeEvaluator<A> attributeEvaluator;
	private PlayerManipulator<A> playerManipulator;

	public PlayerComparator(
			Side side,
			AttributeEvaluator<A> attributeEvaluator,
			PlayerManipulator<A> playerManipulator)
	{
		this.side = side;
		this.attributeEvaluator = attributeEvaluator;
		this.playerManipulator = playerManipulator;
	}

	@Override
	public int compare(Player<A> player1, Player<A> player2)
	{
		return Double.compare(getPlayerRating(player1), getPlayerRating(player2));
	}

	private double getPlayerRating(Player<A> player)
	{
		if (player == null) return Double.MIN_VALUE;

		if (playerRatingCache.containsKey(player))
		{
			return playerRatingCache.get(player);
		}

		double rating = playerManipulator.manipulate(player, attributeEvaluator);

		playerRatingCache.put(player, rating);

		if (!player.getSide().matches(side))
		{
			rating *= 0.84;
		}

		return rating;
	}
}
