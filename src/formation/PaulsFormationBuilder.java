package formation;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.Attributes;
import model.Roster;

public class PaulsFormationBuilder<A extends Attributes>
	implements
		FormationBuilder<A>
{
	@Override
	public List<Formation<A>> createFormations(
		Roster<A> roster,
		List<FormationTemplate<A>> formationTemplates,
		PlayerManipulator<A> manipulator)
	{
		List<Formation<A>> formations = new LinkedList<>();

		List<PositionAssigner<A>> positionAssigners = new LinkedList<>();

		for (FormationTemplate<A> formationTemplate : formationTemplates)
		{
			List<Position<A>> positions = new LinkedList<>();

			for (PositionTemplate<A> positionTemplate : formationTemplate.getPositions())
			{
				if (positionTemplate.isIgnored()) continue;

				Position<A> position = new Position<>(positionTemplate.getName());
				PositionAssigner<A> positionAssigner =
						new PositionAssigner<A>(
							roster,
							positionTemplate,
							position,
							manipulator);

				positions.add(position);
				positionAssigners.add(positionAssigner);
			}

			formations.add(new Formation<>(formationTemplate.getName(), positions));
		}

		assigntPlayersToPositions(positionAssigners);

		return formations;
	}

	private void assigntPlayersToPositions(List<PositionAssigner<A>> positionAssigners)
	{
		Map<Object, List<PositionAssigner<A>>> positionAssignerGroups = positionAssigners
			.stream()
			.collect(Collectors.groupingBy(pa -> pa.getPositionTemplate()));

		int numberOfLayers = calculateNumberOfLayers(positionAssignerGroups);

		for (int i = 0; i < numberOfLayers; i++)
		{
			List<PositionAssigner<A>> subPositionAssigners = new LinkedList<>();

			for (List<PositionAssigner<A>> values : positionAssignerGroups.values())
			{
				int amount = values.size() / numberOfLayers;
				int start = i * amount;

				for (int j = start; j < start + amount; j++)
				{
					subPositionAssigners.add(values.get(j));
				}
			}

			while (!subPositionAssigners.isEmpty())
			{
				Collections.sort(subPositionAssigners);
				PositionAssigner<A> assigner = subPositionAssigners.remove(0);
				assigner.assignBestPlayerToPosition();
			}
		}
	}

	private int calculateNumberOfLayers(
		Map<Object, List<PositionAssigner<A>>> positionAssignerGroups)
	{
		return positionAssignerGroups
			.values()
			.stream()
			.mapToInt(v -> v.size())
			.min()
			.getAsInt();
	}
}
