package builders;

import comparators.RatingComparator;
import evaluators.PlayerEvaluator;
import model.HandballFormation;
import model.HandballFormationBuilder;
import model.HandballFormationTemplate;
import model.Player;
import model.Roster;
import model.Side;
import model.handball.HandballAttributes;

public class DumbFormationBuilder implements HandballFormationBuilder
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
		return new HandballFormation(
				name,
				select(roster, pivotEvaluator, Side.UNIVERSAL),
				select(roster, leftWingEvaluator, Side.LEFT),
				select(roster, rightWingEvaluator, Side.RIGHT),
				select(roster, centerBackEvaluator, Side.UNIVERSAL),
				select(roster, leftBackEvaluator, Side.LEFT),
				select(roster, rightBackEvaluator, Side.RIGHT));
	}

	private static Player<HandballAttributes> select(
			Roster<HandballAttributes> roster,
			PlayerEvaluator<HandballAttributes> evaluator,
			Side side)
	{
		Player<HandballAttributes> player = roster
				.stream()
				.filter(p -> p.getSide().equals(side))
				.max(new RatingComparator<HandballAttributes>(evaluator))
				.get();

		roster.remove(player);

		return player;
	}
}
