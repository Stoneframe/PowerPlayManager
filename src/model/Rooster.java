package model;

import java.util.Collection;
import java.util.LinkedList;

public class Rooster extends LinkedList<Player>
{
	private static final long serialVersionUID = -8152418167319003370L;

	public Rooster()
	{
	}

	public Rooster(Collection<? extends Player> players)
	{
		super(players);
	}

	public Rooster copy()
	{
		return new Rooster(this);
	}
}
