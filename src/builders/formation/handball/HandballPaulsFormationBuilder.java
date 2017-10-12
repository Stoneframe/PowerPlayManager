package builders.formation.handball;

import java.util.LinkedList;
import java.util.List;

import builders.formation.PaulsFormationBuilder;
import model.Roster;
import model.Side;
import model.handball.HandballAttributes;
import model.handball.HandballFormation;

public class HandballPaulsFormationBuilder
	extends PaulsFormationBuilder<HandballAttributes, HandballFormation, HandballFormationTemplate>
{
	@Override
	protected HandballFormation createFormation(String name)
	{
		return new HandballFormation(name);
	}

	@Override
	protected List<PositionAssigner<HandballAttributes>> createPositionAssigners(
			Roster<HandballAttributes> roster,
			HandballFormationTemplate formationTemplate,
			HandballFormation formation)
	{
		List<PositionAssigner<HandballAttributes>> positions =
				new LinkedList<PositionAssigner<HandballAttributes>>();

		positions.add(
			new PositionAssigner<HandballAttributes>(
					roster,
					formationTemplate.getPivotEvaluator(),
					Side.UNIVERSAL,
					(player) -> formation.setPivot(player)));
		positions.add(
			new PositionAssigner<HandballAttributes>(
					roster,
					formationTemplate.getLeftWingEvaluator(),
					Side.LEFT,
					(player) -> formation.setLeftWing(player)));
		positions.add(
			new PositionAssigner<HandballAttributes>(
					roster,
					formationTemplate.getLeftWingEvaluator(),
					Side.RIGHT,
					(player) -> formation.setRightWing(player)));
		positions.add(
			new PositionAssigner<HandballAttributes>(
					roster,
					formationTemplate.getCenterBackEvaluator(),
					Side.UNIVERSAL,
					(player) -> formation.setCenterBack(player)));
		positions.add(
			new PositionAssigner<HandballAttributes>(
					roster,
					formationTemplate.getLeftBackEvaluator(),
					Side.LEFT,
					(player) -> formation.setLeftBack(player)));
		positions.add(
			new PositionAssigner<HandballAttributes>(
					roster,
					formationTemplate.getRightBackEvaluator(),
					Side.RIGHT,
					(player) -> formation.setRightBack(player)));

		return positions;
	}
}
