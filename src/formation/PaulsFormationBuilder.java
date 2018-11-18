package formation;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import model.Attributes;
import model.Player;
import model.Roster;

public class PaulsFormationBuilder<A extends Attributes>
	implements
		FormationBuilder<A>
{
	@Override
	public List<Formation<A>> createFormations(
			Roster<A> roster,
			List<FormationTemplate<A>> formationTemplates,
			PlayerManipulator<A> playerManipulator)
	{
		List<Formation<A>> formations = new LinkedList<>();
		List<PositionAssigner> positionAssigners = new LinkedList<>();

		for (FormationTemplate<A> formationTemplate : formationTemplates)
		{
			List<Position<A>> positions = new LinkedList<>();

			for (PositionTemplate<A> positionTemplate : formationTemplate.getPositions())
			{
				if (positionTemplate.isIgnored()) continue;

				Position<A> position = new Position<>(positionTemplate.getName());
				PositionAssigner positionAssigner =
						new PositionAssigner(
								roster,
								position,
								new PlayerComparator<>(
										positionTemplate.getSide(),
										positionTemplate.getAttributeEvaluator(),
										playerManipulator));

				positions.add(position);
				positionAssigners.add(positionAssigner);
			}

			formations.add(new Formation<>(formationTemplate.getName(), positions));
		}

		Collections.sort(positionAssigners);

		for (PositionAssigner positionAssigner : positionAssigners)
		{
			positionAssigner.assignBestPlayerToPosition();
		}

		return formations;
	}

	protected class PositionAssigner
		implements
			Comparable<PositionAssigner>
	{
		private Roster<A> roster;
		private Position<A> position;
		private PlayerComparator<A> playerComparator;

		public PositionAssigner(
				Roster<A> roster,
				Position<A> position,
				PlayerComparator<A> playerComparator)
		{
			this.roster = roster;
			this.position = position;
			this.playerComparator = playerComparator;
		}

		@Override
		public int compareTo(PositionAssigner other)
		{
			int bestPlayersComparison = comparePlayersAtRank(other, this, 0);

			if (bestPlayersComparison != 0)
			{
				return bestPlayersComparison;
			}

			int rank = 1;

			int comparison;
			while ((comparison = comparePlayersAtRank(this, other, rank)) == 0
					&& rank < roster.size())
			{
				rank++;
			}

			return comparison;
		}

		public void assignBestPlayerToPosition()
		{
			Player<A> player = getBestPlayer();
			position.setPlayer(player);
			roster.remove(player);
		}

		private int comparePlayersAtRank(
				PositionAssigner assigner1,
				PositionAssigner assigner2,
				int rank)
		{
			return playerComparator.compare(
				assigner1.getPlayerAtRank(rank),
				assigner2.getPlayerAtRank(rank));
		}

		private Player<A> getBestPlayer()
		{
			return getPlayerAtRank(0);
		}

		private Player<A> getPlayerAtRank(int rank)
		{
			return roster
				.stream()
				.sorted(playerComparator.reversed())
				.skip(rank)
				.findFirst()
				.orElse(null);
		}
	}
}
