package model.builders;

import java.util.List;
import java.util.stream.Collectors;

import model.Formation;
import model.FormationBuilder;
import model.Player;
import model.PlayerEvaluator;
import model.Rooster;
import model.Side;
import model.comparators.RatingComparator;

public class PaulsFormationBuilder implements FormationBuilder
{
	private static final int NUMBER_OF_PLAYERS = 5;

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
		List<Player> pivots = select(
			rooster,
			pivotEvaluator,
			Side.UNIVERSAL,
			NUMBER_OF_PLAYERS);
		List<Player> leftWings = select(
			rooster,
			leftWingEvaluator,
			Side.LEFT,
			NUMBER_OF_PLAYERS);
		List<Player> rightWings = select(
			rooster,
			rightWingEvaluator,
			Side.RIGHT,
			NUMBER_OF_PLAYERS);
		List<Player> centerBacks = select(
			rooster,
			centerBackEvaluator,
			Side.UNIVERSAL,
			NUMBER_OF_PLAYERS);
		List<Player> leftBacks = select(
			rooster,
			leftBackEvaluator,
			Side.LEFT,
			NUMBER_OF_PLAYERS);
		List<Player> rightBacks = select(
			rooster,
			rightBackEvaluator,
			Side.RIGHT,
			NUMBER_OF_PLAYERS);

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

		return null;
	}

	private static List<Player> select(
			Rooster rooster,
			PlayerEvaluator evaluator,
			Side side,
			int numberOfPlayers)
	{
		List<Player> players = rooster
				.stream()
				.filter(p -> p.getSide().equals(side))
				.sorted(
					(p1, p2) -> new RatingComparator(evaluator).compare(p2, p1))
				.limit(numberOfPlayers)
				.collect(Collectors.toList());

		return players;
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
