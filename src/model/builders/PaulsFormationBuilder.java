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

		List<Position> positions = new LinkedList<Position>();
		positions.add(
			new Position(
					pivotEvaluator,
					Side.UNIVERSAL,
					roster,
					new PositionAssigner()
					{
						public void assign(Player player)
						{
							formation.setPivot(player);
						}
					}));
		positions.add(
			new Position(
					leftWingEvaluator,
					Side.LEFT,
					roster,
					new PositionAssigner()
					{
						public void assign(Player player)
						{
							formation.setLeftWing(player);
						}
					}));
		positions.add(
			new Position(
					rightWingEvaluator,
					Side.RIGHT,
					roster,
					new PositionAssigner()
					{
						public void assign(Player player)
						{
							formation.setRightWing(player);
						}
					}));
		positions.add(
			new Position(
					centerBackEvaluator,
					Side.UNIVERSAL,
					roster,
					new PositionAssigner()
					{
						public void assign(Player player)
						{
							formation.setCenterBack(player);
						}
					}));
		positions.add(
			new Position(
					leftBackEvaluator,
					Side.LEFT,
					roster,
					new PositionAssigner()
					{
						public void assign(Player player)
						{
							formation.setLeftBack(player);
						}
					}));
		positions.add(
			new Position(
					rightBackEvaluator,
					Side.RIGHT,
					roster,
					new PositionAssigner()
					{
						public void assign(Player player)
						{
							formation.setRightBack(player);
						}
					}));

		while (!positions.isEmpty())
		{
			Collections.sort(positions);
			Position position = positions.remove(0);
			position.assingPlayer();
		}

		return formation;
	}

	private class Position implements Comparable<Position>
	{
		private PlayerEvaluator evaluator;
		private Side side;
		private Roster roster;
		private PositionAssigner positionAssigner;

		public Position(
				PlayerEvaluator evaluator,
				Side side,
				Roster roster,
				PositionAssigner positionAssigner)
		{
			this.evaluator = evaluator;
			this.side = side;
			this.roster = roster;
			this.positionAssigner = positionAssigner;
		}

		@Override
		public int compareTo(Position other)
		{
			return Double.compare(
				other.evaluator.getRating(other.bestPlayer().getAttributes()),
				this.evaluator.getRating(this.bestPlayer().getAttributes()));
		}

		public void assingPlayer()
		{
			positionAssigner.assign(bestPlayer());
			roster.remove(bestPlayer());
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

	private interface PositionAssigner
	{
		public void assign(Player player);
	}
}
