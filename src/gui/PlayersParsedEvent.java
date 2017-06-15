package gui;

import java.util.EventObject;
import java.util.List;

import model.Player;

public class PlayersParsedEvent extends EventObject
{
	private static final long serialVersionUID = -3051108627753324500L;

	private List<Player> players;

	public PlayersParsedEvent(Object source, List<Player> players)
	{
		super(source);

		this.players = players;
	}

	public List<Player> getPlayers()
	{
		return players;
	}
}
