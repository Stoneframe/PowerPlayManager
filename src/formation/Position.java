package formation;

import model.Attributes;
import model.Player;

public class Position<A extends Attributes>
{
	private String name;

	private Player<A> player;

	public Position(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public Player<A> getPlayer()
	{
		return player;
	}

	public void setPlayer(Player<A> player)
	{
		this.player = player;
	}
}
