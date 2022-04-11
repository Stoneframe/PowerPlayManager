package formation;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import evaluators.AttributeEvaluator;
import model.Attributes;
import model.Player;
import model.Roster;

public class PositionAssigner<A extends Attributes>
	implements
		Comparable<PositionAssigner<A>>
{
	private Map<Player<A>, Double> playerRatingCache = new HashMap<>();

	private Roster<A> roster;
	private PositionTemplate<A> positionTemplate;
	private Position<A> position;

	private PlayerManipulator<A> manipulator;

	public PositionAssigner(
		Roster<A> roster,
		PositionTemplate<A> positionTemplate,
		Position<A> position,
		PlayerManipulator<A> manipulator)
	{
		this.roster = roster;
		this.positionTemplate = positionTemplate;
		this.position = position;
		this.manipulator = manipulator;
	}

	public PositionTemplate<A> getPositionTemplate()
	{
		return positionTemplate;
	}

	@Override
	public int compareTo(PositionAssigner<A> other)
	{
		int bestPlayersComparison = comparePlayersAtRank(other, this, 0);

		if (bestPlayersComparison != 0)
		{
			return bestPlayersComparison;
		}

		int rank = 1;

		int comparison;
		while ((comparison = comparePlayersAtRank(this, other, rank)) == 0 && rank < roster.size())
		{
			rank++;
		}

		return comparison;
	}

	public void assignBestPlayerToPosition()
	{
		Player<A> player = getBestPlayer();
		position.setPlayer(player);
		roster.remove(player);
	}

	private static int comparePlayersAtRank(
		PositionAssigner<?> assigner1,
		PositionAssigner<?> assigner2,
		int rank)
	{
		return Double.compare(
			assigner1.getRatingOfPlayerAtRank(rank),
			assigner2.getRatingOfPlayerAtRank(rank));
	}

	private double getRatingOfPlayerAtRank(int rank)
	{
		Player<A> player = getPlayerAtRank(rank);

		if (player == null)
		{
			return Double.MIN_VALUE;
		}

		return getPlayerRating(player);
	}

	private Player<A> getBestPlayer()
	{
		return getPlayerAtRank(0);
	}

	private Player<A> getPlayerAtRank(int rank)
	{
		return roster
			.stream()
			.sorted(
				Comparator
					.comparingDouble((Player<A> p) -> getPlayerRating(p))
					.reversed())
			.skip(rank)
			.findFirst()
			.orElse(null);
	}

	private double getPlayerRating(Player<A> player)
	{
		AttributeEvaluator<A> attributeEvaluator = positionTemplate.getAttributeEvaluator();

		if (playerRatingCache.containsKey(player))
		{
			return playerRatingCache.get(player);
		}

		double rating = manipulator.manipulate(player, attributeEvaluator);

		if (!player.getSide().matches(positionTemplate.getSide()))
		{
			rating *= 0.84;
		}

		playerRatingCache.put(player, rating);

		return rating;
	}
}