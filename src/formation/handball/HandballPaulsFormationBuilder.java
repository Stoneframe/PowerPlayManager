package formation.handball;

import java.util.LinkedList;
import java.util.List;

import formation.PaulsFormationBuilder;
import model.Roster;
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
					formationTemplate.getPositions().get(0),
					(player) -> formation.setPivot(player)));

		positions.add(
			new PositionAssigner<HandballAttributes>(
					roster,
					formationTemplate.getPositions().get(1),
					(player) -> formation.setLeftWing(player)));

		positions.add(
			new PositionAssigner<HandballAttributes>(
					roster,
					formationTemplate.getPositions().get(2),
					(player) -> formation.setRightWing(player)));

		positions.add(
			new PositionAssigner<HandballAttributes>(
					roster,
					formationTemplate.getPositions().get(3),
					(player) -> formation.setCenterBack(player)));

		positions.add(
			new PositionAssigner<HandballAttributes>(
					roster,
					formationTemplate.getPositions().get(4),
					(player) -> formation.setLeftBack(player)));

		positions.add(
			new PositionAssigner<HandballAttributes>(
					roster,
					formationTemplate.getPositions().get(5),
					(player) -> formation.setRightBack(player)));

		return positions;
	}
}
