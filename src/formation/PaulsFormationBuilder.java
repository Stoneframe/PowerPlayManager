package formation;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

import comparators.RatingComparator;
import evaluators.AttributeEvaluator;
import model.Attributes;
import model.Formation;
import model.Player;
import model.Roster;
import model.Side;

public abstract class PaulsFormationBuilder<
		A extends Attributes,
		F extends Formation,
		FT extends FormationTemplate>
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
		private AttributeEvaluator<A> evaluator;
		private Side side;
		private Roster<A> roster;
		private Consumer<Player<A>> assignAction;

		public PositionAssigner(
				Roster<A> roster,
				AttributeEvaluator<A> evaluator,
				Side side,
				Consumer<Player<A>> assignAction)
		{
			this.evaluator = evaluator;
			this.side = side;
			this.roster = roster;
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

			return evaluator.getRating(player.getAttributes());
		}

		private Player<A> getBestPlayer()
		{
			return getPlayerAtRank(0);
		}

		private Player<A> getPlayerAtRank(int rank)
		{
			return roster
					.stream()
					.filter(p -> p.getSide().equals(side))
					.sorted(new RatingComparator<>(evaluator).reversed())
					.skip(rank)
					.findFirst()
					.orElse(null);
		}
	}
}
