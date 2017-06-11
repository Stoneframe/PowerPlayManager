package model.builders;

import java.util.List;

import model.Formation;
import model.FormationBuilder;
import model.Player;
import model.PlayerEvaluator;
import model.Rooster;
import model.Side;

public class PaulsFormationBuilder implements FormationBuilder
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
		List<Player> pivots = rooster
				.selectSeveral(pivotEvaluator, Side.UNIVERSAL, 5);
		List<Player> leftWings = rooster
				.selectSeveral(leftWingEvaluator, Side.LEFT, 5);
		List<Player> rightWings = rooster
				.selectSeveral(rightWingEvaluator, Side.RIGHT, 5);
		List<Player> centerBacks = rooster
				.selectSeveral(centerBackEvaluator, Side.UNIVERSAL, 5);
		List<Player> leftBacks = rooster
				.selectSeveral(leftBackEvaluator, Side.LEFT, 5);
		List<Player> rightBacks = rooster
				.selectSeveral(rightBackEvaluator, Side.RIGHT, 5);

		System.out.println("===Pivots=========");
		print(pivots, pivotEvaluator);
		System.out.println("===Left Wings=====");
		print(leftWings, leftWingEvaluator);
		System.out.println("===Right Wings====");
		print(rightWings, rightWingEvaluator);
		System.out.println("===Center Backs===");
		print(centerBacks, centerBackEvaluator);
		System.out.println("===Left Backs=====");
		print(leftBacks, leftBackEvaluator);
		System.out.println("===Right Backs====");
		print(rightBacks, rightBackEvaluator);

		return new Formation(
				rooster.select(pivotEvaluator, Side.UNIVERSAL),
				rooster.select(leftWingEvaluator, Side.LEFT),
				rooster.select(rightWingEvaluator, Side.RIGHT),
				rooster.select(centerBackEvaluator, Side.UNIVERSAL),
				rooster.select(leftBackEvaluator, Side.LEFT),
				rooster.select(rightBackEvaluator, Side.RIGHT));
	}

	private static void print(
			Iterable<Player> players,
			PlayerEvaluator... evaluators)
	{
		for (Player player : players)
		{
			System.out.println(player);

			for (PlayerEvaluator evaluator : evaluators)
			{
				System.out.println(String.format(
					"%s: %.1f(%.1f)",
					evaluator.getName(),
					evaluator.getRating(player.getAttributes()),
					evaluator.getQuality(player.getAttributes())));
			}

			System.out.println();
		}
	}
}
