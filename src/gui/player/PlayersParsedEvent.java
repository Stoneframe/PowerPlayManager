package gui.player;

import java.util.EventObject;
import java.util.List;

import model.Attributes;
import model.Player;

public class PlayersParsedEvent<A extends Attributes>
	extends EventObject
{
	private static final long serialVersionUID = -3051108627753324500L;

	private List<Player<A>> players;

	public PlayersParsedEvent(Object source, List<Player<A>> players)
	{
		super(source);

		this.players = players;
	}

	public List<Player<A>> getPlayers()
	{
		return players;
	}
}
