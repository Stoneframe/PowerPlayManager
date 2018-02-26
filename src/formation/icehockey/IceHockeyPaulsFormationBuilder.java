package formation.icehockey;

import java.util.LinkedList;
import java.util.List;

import formation.PaulsFormationBuilder;
import model.Roster;
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
		List<PositionAssigner<IceHockeyAttributes>> positions =
				new LinkedList<PositionAssigner<IceHockeyAttributes>>();

		positions.add(
			new PositionAssigner<IceHockeyAttributes>(
					roster,
					formationTemplate.getPositions().get(0),
					(player) -> formation.setLeftWing(player)));

		positions.add(
			new PositionAssigner<IceHockeyAttributes>(
					roster,
					formationTemplate.getPositions().get(1),
					(player) -> formation.setCenter(player)));

		positions.add(
			new PositionAssigner<IceHockeyAttributes>(
					roster,
					formationTemplate.getPositions().get(2),
					(player) -> formation.setRightWing(player)));

		positions.add(
			new PositionAssigner<IceHockeyAttributes>(
					roster,
					formationTemplate.getPositions().get(3),
					(player) -> formation.setLeftBack(player)));

		positions.add(
			new PositionAssigner<IceHockeyAttributes>(
					roster,
					formationTemplate.getPositions().get(4),
					(player) -> formation.setRightBack(player)));

		return positions;
	}
}
