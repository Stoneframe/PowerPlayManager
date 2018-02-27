package formation;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import comparators.RatingComparator;
import model.Attributes;
import model.Player;
import model.Roster;

public class PaulsFormationBuilder<A extends Attributes>
	implements
		FormationBuilder<A>
{
	@Override
	public List<Formation<A>> createFormations(
			Roster<A> roster,
			List<FormationTemplate<A>> formationTemplates)
	{
		List<Formation<A>> formations = new LinkedList<>();

		List<PositionAssigner<A>> positionAssigners = new LinkedList<>();

		for (FormationTemplate<A> formationTemplate : formationTemplates)
		{
			List<Position<A>> positions = new LinkedList<>();

			for (PositionTemplate<A> positionTemplate : formationTemplate.getPositions())
			{
				if (positionTemplate.isIgnored()) continue;

				Position<A> position = new Position<>(positionTemplate.getName());
				positions.add(position);
				positionAssigners.add(new PositionAssigner<>(roster, positionTemplate, position));
			}

			formations.add(new Formation<>(formationTemplate.getName(), positions));
		}

		while (!positionAssigners.isEmpty())
		{
			Collections.sort(positionAssigners);
			PositionAssigner<A> assigner = positionAssigners.remove(0);
			assigner.assignBestPlayerToPosition();
		}

		return formations;
	}

	protected static class PositionAssigner<A extends Attributes>
		implements
			Comparable<PositionAssigner<A>>
	{
		private Roster<A> roster;
		private PositionTemplate<A> positionTemplate;
		private Position<A> position;

		public PositionAssigner(
				Roster<A> roster,
				PositionTemplate<A> positionTemplate,
				Position<A> position)
		{
			this.roster = roster;
			this.positionTemplate = positionTemplate;
			this.position = position;
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

			double rating =
					positionTemplate.getAttributeEvaluator().getRating(player.getAttributes());

			if (!player.getSide().matches(positionTemplate.getSide()))
			{
				rating *= 0.84;
			}

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
						new RatingComparator<A>(positionTemplate.getAttributeEvaluator())
								.reversed())
					.skip(rank)
					.findFirst()
					.orElse(null);
		}
	}
}
