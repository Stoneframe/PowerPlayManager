package builders.formation;

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
	public F createFormation(Roster<A> roster, FT template)
	{
		F formation = createFormation(template.getName());

		List<PositionAssigner<A>> positionAssigners =
				createPositionAssigners(roster, template, formation);

		while (!positionAssigners.isEmpty())
		{
			Collections.sort(positionAssigners);
			PositionAssigner<A> assigner = positionAssigners.remove(0);
			assigner.assignPosition();
		}

		return formation;
	}

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
			return Double.compare(
				other.evaluator.getRating(other.preferedPlayer().getAttributes()),
				this.evaluator.getRating(this.preferedPlayer().getAttributes()));
		}

		public void assignPosition()
		{
			Player<A> player = preferedPlayer();
			assignAction.accept(player);
			roster.remove(player);
		}

		private Player<A> preferedPlayer()
		{
			Player<A> player = roster
					.stream()
					.filter(p -> p.getSide().equals(side))
					.max(new RatingComparator<A>(evaluator))
					.get();

			return player;
		}
	}
}
