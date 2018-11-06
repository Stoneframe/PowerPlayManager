package formation;

import java.util.List;
import java.util.stream.Collectors;

import model.Attributes;
import model.Player;

public class Formation<A extends Attributes>
{
	protected String name;

	private List<Position<A>> positions;

	public Formation(String name, List<Position<A>> positions)
	{
		this.name = name;
		this.positions = positions;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public List<Position<A>> getPositions()
	{
		return positions;
	}

	public List<Player<A>> getPlayers()
	{
		return positions.stream().map(p -> p.getPlayer()).collect(Collectors.toList());
	}
}
