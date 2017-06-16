package model.builders;

import model.Formation;
import model.FormationBuilder;
import model.Player;
import model.PlayerEvaluator;
import model.Roster;
import model.Side;
import model.comparators.RatingComparator;

public class DumbFormationBuilder implements FormationBuilder
{
	@Override
	public Formation createFormation(
			Roster roster,
			String name,
			PlayerEvaluator pivotEvaluator,
			PlayerEvaluator leftWingEvaluator,
			PlayerEvaluator rightWingEvaluator,
			PlayerEvaluator centerBackEvaluator,
			PlayerEvaluator leftBackEvaluator,
			PlayerEvaluator rightBackEvaluator)
	{
		return new Formation(
				name,
				select(roster, pivotEvaluator, Side.UNIVERSAL),
				select(roster, leftWingEvaluator, Side.LEFT),
				select(roster, rightWingEvaluator, Side.RIGHT),
				select(roster, centerBackEvaluator, Side.UNIVERSAL),
				select(roster, leftBackEvaluator, Side.LEFT),
				select(roster, rightBackEvaluator, Side.RIGHT));
	}

	private static Player select(
			Roster roster,
			PlayerEvaluator evaluator,
			Side side)
	{
		Player player = roster
				.stream()
				.filter(p -> p.getSide().equals(side))
				.max(new RatingComparator(evaluator))
				.get();

		roster.remove(player);

		return player;
	}
}
