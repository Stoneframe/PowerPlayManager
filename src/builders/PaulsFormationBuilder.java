package builders;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

import comparators.RatingComparator;
import evaluators.PlayerEvaluator;
import model.HandballFormation;
import model.HandballFormationBuilder;
import model.HandballFormationTemplate;
import model.Player;
import model.Roster;
import model.Side;
import model.handball.HandballAttributes;

public class PaulsFormationBuilder implements HandballFormationBuilder
{
	@Override
	public HandballFormation createFormation(
			Roster<HandballAttributes> roster,
			HandballFormationTemplate template)
	{
		return createFormation(
			roster,
			template.getName(),
			template.getPivotEvaluator(),
			template.getLeftWingEvaluator(),
			template.getRightWingEvaluator(),
			template.getCenterBackEvaluator(),
			template.getLeftBackEvaluator(),
			template.getRightBackEvaluator());
	}

	@Override
	public HandballFormation createFormation(
			Roster<HandballAttributes> roster,
			String name,
			PlayerEvaluator<HandballAttributes> pivotEvaluator,
			PlayerEvaluator<HandballAttributes> leftWingEvaluator,
			PlayerEvaluator<HandballAttributes> rightWingEvaluator,
			PlayerEvaluator<HandballAttributes> centerBackEvaluator,
			PlayerEvaluator<HandballAttributes> leftBackEvaluator,
			PlayerEvaluator<HandballAttributes> rightBackEvaluator)
	{
		HandballFormation formation = new HandballFormation(name);

		List<PositionAssigner> positionAssigners = createPositionAssigners(
			roster,
			pivotEvaluator,
			leftWingEvaluator,
			rightWingEvaluator,
			centerBackEvaluator,
			leftBackEvaluator,
			rightBackEvaluator,
			formation);

		while (!positionAssigners.isEmpty())
		{
			Collections.sort(positionAssigners);
			PositionAssigner assigner = positionAssigners.remove(0);
			assigner.assignPosition();
		}

		return formation;
	}

	private List<PositionAssigner> createPositionAssigners(
			Roster<HandballAttributes> roster,
			PlayerEvaluator<HandballAttributes> pivotEvaluator,
			PlayerEvaluator<HandballAttributes> leftWingEvaluator,
			PlayerEvaluator<HandballAttributes> rightWingEvaluator,
			PlayerEvaluator<HandballAttributes> centerBackEvaluator,
			PlayerEvaluator<HandballAttributes> leftBackEvaluator,
			PlayerEvaluator<HandballAttributes> rightBackEvaluator,
			HandballFormation formation)
	{
		List<PositionAssigner> positions = new LinkedList<PositionAssigner>();

		positions.add(
			new PositionAssigner(
					roster,
					pivotEvaluator,
					Side.UNIVERSAL,
					(player) -> formation.setPivot(player)));
		positions.add(
			new PositionAssigner(
					roster,
					leftWingEvaluator,
					Side.LEFT,
					(player) -> formation.setLeftWing(player)));
		positions.add(
			new PositionAssigner(
					roster,
					rightWingEvaluator,
					Side.RIGHT,
					(player) -> formation.setRightWing(player)));
		positions.add(
			new PositionAssigner(
					roster,
					centerBackEvaluator,
					Side.UNIVERSAL,
					(player) -> formation.setCenterBack(player)));
		positions.add(
			new PositionAssigner(
					roster,
					leftBackEvaluator,
					Side.LEFT,
					(player) -> formation.setLeftBack(player)));
		positions.add(
			new PositionAssigner(
					roster,
					rightBackEvaluator,
					Side.RIGHT,
					(player) -> formation.setRightBack(player)));

		return positions;
	}

	private class PositionAssigner implements Comparable<PositionAssigner>
	{
		private PlayerEvaluator<HandballAttributes> evaluator;
		private Side side;
		private Roster<HandballAttributes> roster;
		private Consumer<Player<HandballAttributes>> assignAction;

		public PositionAssigner(
				Roster<HandballAttributes> roster,
				PlayerEvaluator<HandballAttributes> evaluator,
				Side side,
				Consumer<Player<HandballAttributes>> assignAction)
		{
			this.evaluator = evaluator;
			this.side = side;
			this.roster = roster;
			this.assignAction = assignAction;
		}

		@Override
		public int compareTo(PositionAssigner other)
		{
			return Double.compare(
				other.evaluator
						.getRating(other.preferedPlayer().getAttributes()),
				this.evaluator
						.getRating(this.preferedPlayer().getAttributes()));
		}

		public void assignPosition()
		{
			Player<HandballAttributes> player = preferedPlayer();
			assignAction.accept(player);
			roster.remove(player);
		}

		private Player<HandballAttributes> preferedPlayer()
		{
			Player<HandballAttributes> player = roster
					.stream()
					.filter(p -> p.getSide().equals(side))
					.max(new RatingComparator<HandballAttributes>(evaluator))
					.get();

			return player;
		}
	}
}
