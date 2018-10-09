package formation;

import java.util.List;

import model.Attributes;

public class FormationTemplate<A extends Attributes>
{
	private String name;

	protected List<PositionTemplate<A>> positions;

	public FormationTemplate(String name, List<PositionTemplate<A>> positions)
	{
		this.name = name;
		this.positions = positions;
	}

	public String getName()
	{
		return name;
	}

	public List<PositionTemplate<A>> getPositions()
	{
		return positions;
	}

	public int getNumberOfRequiredPlayers()
	{
		return positions.size();
	}

	@Override
	public String toString()
	{
		return getName();
	}
}
