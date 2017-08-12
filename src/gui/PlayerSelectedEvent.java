package gui;

import java.util.EventObject;

import model.Attributes;
import model.Player;

public class PlayerSelectedEvent<A extends Attributes> extends EventObject
{
	private static final long serialVersionUID = -1549968126000310448L;

	private Player<A> player;

	public PlayerSelectedEvent(Object source, Player<A> player)
	{
		super(source);

		this.player = player;
	}

	public Player<A> getPlayer()
	{
		return player;
	}
}
