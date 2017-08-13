package builders.handball;

import builders.formation.FormationBuilder;
import comparators.RatingComparator;
import evaluators.PlayerEvaluator;
import model.Player;
import model.Roster;
import model.Side;
import model.handball.HandballAttributes;
import model.handball.HandballFormation;

public class DumbHandballFormationBuilder implements
		FormationBuilder<HandballAttributes, HandballFormation, HandballFormationTemplate>
{
	@Override
	public HandballFormation createFormation(
			Roster<HandballAttributes> roster,
			HandballFormationTemplate template)
	{
		return new HandballFormation(
				template.getName(),
				select(roster, template.getPivotEvaluator(), Side.UNIVERSAL),
				select(roster, template.getLeftWingEvaluator(), Side.LEFT),
				select(roster, template.getRightWingEvaluator(), Side.RIGHT),
				select(roster, template.getCenterBackEvaluator(), Side.UNIVERSAL),
				select(roster, template.getLeftBackEvaluator(), Side.LEFT),
				select(roster, template.getRightBackEvaluator(), Side.RIGHT));
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
