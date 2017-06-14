package gui;

import java.util.EventObject;

import model.Roster;

public class RosterParsedEvent extends EventObject
{
	private static final long serialVersionUID = -3051108627753324500L;

	private Roster roster;

	public RosterParsedEvent(Object source, Roster roster)
	{
		super(source);

		this.roster = roster;
	}

	public Roster getRoster()
	{
		return roster;
	}
}
