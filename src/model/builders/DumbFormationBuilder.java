package model.builders;

import model.Formation;
import model.FormationBuilder;
import model.Player;
import model.PlayerEvaluator;
import model.Rooster;
import model.Side;
import model.comparators.RatingComparator;

public class DumbFormationBuilder implements FormationBuilder
{
	@Override
	public Formation create(
			Rooster rooster,
			PlayerEvaluator pivotEvaluator,
			PlayerEvaluator leftWingEvaluator,
			PlayerEvaluator rightWingEvaluator,
			PlayerEvaluator centerBackEvaluator,
			PlayerEvaluator leftBackEvaluator,
			PlayerEvaluator rightBackEvaluator)
	{
		return new Formation(
				select(rooster, pivotEvaluator, Side.UNIVERSAL),
				select(rooster, leftWingEvaluator, Side.LEFT),
				select(rooster, rightWingEvaluator, Side.RIGHT),
				select(rooster, centerBackEvaluator, Side.UNIVERSAL),
				select(rooster, leftBackEvaluator, Side.LEFT),
				select(rooster, rightBackEvaluator, Side.RIGHT));
	}

	private static Player select(
			Rooster rooster,
			PlayerEvaluator evaluator,
			Side side)
	{
		Player player = rooster
				.stream()
				.filter(p -> p.getSide().equals(side))
				.max(new RatingComparator(evaluator))
				.get();

		rooster.remove(player);

		return player;
	}
}
