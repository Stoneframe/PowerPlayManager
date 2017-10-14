package formation;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

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
			assigner.assignPosition();
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
			Player<A> thisBestPlayer = this.getBestPlayer();
			Player<A> otherBestPlayer = other.getBestPlayer();

			int bestPlayersComparison = Double.compare(
				other.evaluator.getRating(otherBestPlayer.getAttributes()),
				this.evaluator.getRating(thisBestPlayer.getAttributes()));

			if (bestPlayersComparison != 0)
			{
				return bestPlayersComparison;
			}

			Player<A> thisSecondBestPlayer = this.getSecondBestPlayer();
			Player<A> otherSecondBestPlayer = other.getSecondBestPlayer();

			return Double.compare(
				this.evaluator.getRating(thisSecondBestPlayer.getAttributes()),
				other.evaluator.getRating(otherSecondBestPlayer.getAttributes()));
		}

		public void assignPosition()
		{
			Player<A> player = getBestPlayer();
			assignAction.accept(player);
			roster.remove(player);
		}

		private Player<A> getBestPlayer()
		{
			return getSortedPlayerStream().findFirst().get();
		}

		private Player<A> getSecondBestPlayer()
		{
			return getSortedPlayerStream().skip(1).findFirst().get();
		}

		private Stream<Player<A>> getSortedPlayerStream()
		{
			return roster
					.stream()
					.filter(p -> p.getSide().equals(side))
					.sorted(new RatingComparator<>(evaluator).reversed());
		}
	}
}
