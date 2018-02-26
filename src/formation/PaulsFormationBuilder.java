package formation;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

import comparators.RatingComparator;
import model.Attributes;
import model.Formation;
import model.Player;
import model.Roster;

public abstract class PaulsFormationBuilder<
		A extends Attributes,
		F extends Formation,
		FT extends FormationTemplate<A>>
	implements
		FormationBuilder<A, F, FT>
{
	@Override
	public List<F> createFormations(Roster<A> roster, List<FT> formationTemplates)
	{
		List<F> formations = new LinkedList<>();

		List<PositionAssigner<A>> positionAssigners = new LinkedList<>();

		for (FT formationTemplate : formationTemplates)
		{
			F formation = createFormation(formationTemplate.getName());

			formations.add(formation);

			positionAssigners.addAll(createPositionAssigners(roster, formationTemplate, formation));
			positionAssigners.removeIf(pa -> pa.position.isIgnored());
		}

		while (!positionAssigners.isEmpty())
		{
			Collections.sort(positionAssigners);
			PositionAssigner<A> assigner = positionAssigners.remove(0);
			assigner.assignBestPlayerToPosition();
		}

		return formations;
	}

	protected abstract F createFormation(String name);

	protected abstract List<PositionAssigner<A>> createPositionAssigners(
			Roster<A> roster,
			FT formationTemplate,
			F formation);

	protected static class PositionAssigner<A extends Attributes>
		implements
			Comparable<PositionAssigner<A>>
	{
		private Roster<A> roster;
		private Position<A> position;
		private Consumer<Player<A>> assignAction;

		public PositionAssigner(
				Roster<A> roster,
				Position<A> position,
				Consumer<Player<A>> assignAction)
		{
			this.roster = roster;
			this.position = position;
			this.assignAction = assignAction;
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
			assignAction.accept(player);
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

			double rating = position.getAttributeEvaluator().getRating(player.getAttributes());

			if (!player.getSide().matches(position.getSide()))
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
					.sorted(new RatingComparator<A>(position.getAttributeEvaluator()).reversed())
					.skip(rank)
					.findFirst()
					.orElse(null);
		}
	}
}
