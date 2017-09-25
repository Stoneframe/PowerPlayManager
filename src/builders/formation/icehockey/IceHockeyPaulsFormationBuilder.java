package builders.formation.icehockey;

import java.util.LinkedList;
import java.util.List;

import builders.formation.PaulsFormationBuilder;
import model.Roster;
import model.Side;
import model.icehockey.IceHockeyAttributes;
import model.icehockey.IceHockeyFormation;

public class IceHockeyPaulsFormationBuilder
		extends
		PaulsFormationBuilder<IceHockeyAttributes, IceHockeyFormation, IceHockeyFormationTemplate>
{
	@Override
	protected IceHockeyFormation createFormation(String name)
	{
		return new IceHockeyFormation(name);
	}

	@Override
	protected List<PositionAssigner<IceHockeyAttributes>> createPositionAssigners(
			Roster<IceHockeyAttributes> roster,
			IceHockeyFormationTemplate formationTemplate,
			IceHockeyFormation formation)
	{
		List<PositionAssigner<IceHockeyAttributes>> positions = new LinkedList<PositionAssigner<IceHockeyAttributes>>();

		positions.add(
			new PositionAssigner<IceHockeyAttributes>(
					roster,
					formationTemplate.getLeftWingEvaluator(),
					Side.LEFT,
					(player) -> formation.setLeftWing(player)));

		positions.add(
			new PositionAssigner<IceHockeyAttributes>(
					roster,
					formationTemplate.getCenterEvaluator(),
					Side.UNIVERSAL,
					(player) -> formation.setCenter(player)));

		positions.add(
			new PositionAssigner<IceHockeyAttributes>(
					roster,
					formationTemplate.getRightWingEvaluator(),
					Side.RIGHT,
					(player) -> formation.setRightWing(player)));

		positions.add(
			new PositionAssigner<IceHockeyAttributes>(
					roster,
					formationTemplate.getLeftBackEvaluator(),
					Side.LEFT,
					(player) -> formation.setLeftBack(player)));

		positions.add(
			new PositionAssigner<IceHockeyAttributes>(
					roster,
					formationTemplate.getRightBackEvaluator(),
					Side.RIGHT,
					(player) -> formation.setRightBack(player)));

		return positions;
	}
}
