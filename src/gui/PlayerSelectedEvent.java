package gui;

import java.util.EventObject;

import model.Player;

public class PlayerSelectedEvent extends EventObject
{
	private static final long serialVersionUID = -1549968126000310448L;

	private Player<?> player;

	public PlayerSelectedEvent(Object source, Player<?> player)
	{
		super(source);

		this.player = player;
	}

	public Player<?> getPlayer()
	{
		return player;
	}
}
