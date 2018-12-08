package formation;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import evaluators.AttributeEvaluator;
import model.Attributes;
import model.Player;
import model.Roster;

public class PaulsFormationBuilder2<A extends Attributes>
	implements
		FormationBuilder<A>
{
	@Override
	public List<Formation<A>> createFormations(
			Roster<A> roster,
			List<FormationTemplate<A>> formationTemplates,
			PlayerManipulator<A> manipulator)
	{
		List<Formation<A>> formations = new LinkedList<>();

		List<PositionAssigner> positionAssigners = new LinkedList<>();

		for (FormationTemplate<A> formationTemplate : formationTemplates)
		{
			List<Position<A>> positions = new LinkedList<>();

			for (PositionTemplate<A> positionTemplate : formationTemplate.getPositions())
			{
				if (positionTemplate.isIgnored()) continue;

				Position<A> position = new Position<>(positionTemplate.getName());
				PositionAssigner positionAssigner =
						new PositionAssigner(
								roster,
								positionTemplate,
								position,
								manipulator);

				positions.add(position);
				positionAssigners.add(positionAssigner);
			}

			formations.add(new Formation<>(formationTemplate.getName(), positions));
		}

		assigntPlayersToPositions(positionAssigners);

		return formations;
	}

	private void assigntPlayersToPositions(List<PositionAssigner> positionAssigners)
	{
		Map<PositionTemplate<A>,
				List<PositionAssigner>> equivalentPositionsGroups = positionAssigners
					.stream()
					.collect(Collectors.groupingBy(pa -> pa.positionTemplate));

		int numberOfEquivalentFormations =
				calculateNumberOfEquivalentFormations(equivalentPositionsGroups);

		for (int i = 0; i < numberOfEquivalentFormations; i++)
		{
			List<PositionAssigner> subPositionAssigners = new LinkedList<>();

			for (PositionTemplate<A> key : equivalentPositionsGroups.keySet())
			{
				List<PositionAssigner> values = equivalentPositionsGroups.get(key);

				int amount = values.size() / numberOfEquivalentFormations;
				int start = i * amount;

				for (int j = start; j < start + amount; j++)
				{
					subPositionAssigners.add(values.get(j));
				}
			}

			while (!subPositionAssigners.isEmpty())
			{
				Collections.sort(subPositionAssigners);
				PositionAssigner assigner = subPositionAssigners.remove(0);
				assigner.assignBestPlayerToPosition();
			}
		}
	}

	private int calculateNumberOfEquivalentFormations(
			Map<PositionTemplate<A>, List<PositionAssigner>> positionAssignerGroups)
	{
		return positionAssignerGroups
			.values()
			.stream()
			.mapToInt(v -> v.size())
			.min()
			.getAsInt();
	}

	protected class PositionAssigner
		implements
			Comparable<PositionAssigner>
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

		@Override
		public int compareTo(PositionAssigner other)
		{
			int bestPlayersComparison = comparePlayersAtRank(other, this, 0);

			if (bestPlayersComparison != 0)
			{
				return bestPlayersComparison;
			}

			int rank = 1;

			int comparison;
			while ((comparison = comparePlayersAtRank(this, other, rank)) == 0
					&& rank < roster.size())
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

		private int comparePlayersAtRank(
				PositionAssigner assigner1,
				PositionAssigner assigner2,
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

			double rating = getPlayerRating(player);

			return rating;
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
}
