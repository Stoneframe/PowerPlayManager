package model.builders;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

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
	public Formation create(
			Roster roster,
			PlayerEvaluator pivotEvaluator,
			PlayerEvaluator leftWingEvaluator,
			PlayerEvaluator rightWingEvaluator,
			PlayerEvaluator centerBackEvaluator,
			PlayerEvaluator leftBackEvaluator,
			PlayerEvaluator rightBackEvaluator)
	{
		Formation formation = new Formation();

		List<PositionAssigner> positions = createPositionAssigners(
			roster,
			pivotEvaluator,
			leftWingEvaluator,
			rightWingEvaluator,
			centerBackEvaluator,
			leftBackEvaluator,
			rightBackEvaluator,
			formation);

		while (!positions.isEmpty())
		{
			Collections.sort(positions);
			PositionAssigner position = positions.remove(0);
			position.assignPlayer();
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
		private Consumer<Player> assigner;

		public PositionAssigner(
				Roster roster,
				PlayerEvaluator evaluator,
				Side side,
				Consumer<Player> assigner)
		{
			this.evaluator = evaluator;
			this.side = side;
			this.roster = roster;
			this.assigner = assigner;
		}

		@Override
		public int compareTo(PositionAssigner other)
		{
			return Double.compare(
				other.evaluator.getRating(other.bestPlayer().getAttributes()),
				this.evaluator.getRating(this.bestPlayer().getAttributes()));
		}

		public void assignPlayer()
		{
			Player player = bestPlayer();
			assigner.accept(player);
			roster.remove(player);
		}

		private Player bestPlayer()
		{
			Player player = roster
					.stream()
					.filter(p -> p.getSide().equals(side))
					.max(new RatingComparator(evaluator))
					.get();

			return player;
		}
	}
}
