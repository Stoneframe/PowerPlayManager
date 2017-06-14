package model.builders;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import model.Formation;
import model.FormationBuilder;
import model.Player;
import model.PlayerEvaluator;
import model.Roster;
import model.Side;
import model.comparators.RatingComparator;

public class PaulsFormationBuilder implements FormationBuilder
{
	@Override
	public Formation createFormation(
			Roster roster,
			PlayerEvaluator pivotEvaluator,
			PlayerEvaluator leftWingEvaluator,
			PlayerEvaluator rightWingEvaluator,
			PlayerEvaluator centerBackEvaluator,
			PlayerEvaluator leftBackEvaluator,
			PlayerEvaluator rightBackEvaluator)
	{
		Formation formation = new Formation();

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
			Roster roster,
			PlayerEvaluator pivotEvaluator,
			PlayerEvaluator leftWingEvaluator,
			PlayerEvaluator rightWingEvaluator,
			PlayerEvaluator centerBackEvaluator,
			PlayerEvaluator leftBackEvaluator,
			PlayerEvaluator rightBackEvaluator,
			Formation formation)
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
		private PlayerEvaluator evaluator;
		private Side side;
		private Roster roster;
		private AssignAction assignAction;

		public PositionAssigner(
				Roster roster,
				PlayerEvaluator evaluator,
				Side side,
				AssignAction assignAction)
		{
			this.evaluator = evaluator;
			this.side = side;
			this.roster = roster;
			this.assignAction = assignAction;
		}

		@Override
		public int compareTo(PositionAssigner other)
		{
			return Double
					.compare(
						other.evaluator.getRating(other
								.preferedPlayer()
								.getAttributes()),
						this.evaluator.getRating(this
								.preferedPlayer()
								.getAttributes()));
		}

		public void assignPosition()
		{
			Player player = preferedPlayer();
			assignAction.assignToPosition(player);
			roster.remove(player);
		}

		private Player preferedPlayer()
		{
			Player player = roster
					.stream()
					.filter(p -> p.getSide().equals(side))
					.max(new RatingComparator(evaluator))
					.get();

			return player;
		}
	}

	private interface AssignAction
	{
		public void assignToPosition(Player player);
	}
}
