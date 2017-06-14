package gui;

import java.util.EventListener;

public interface RosterParsedListener extends EventListener
{
	public void rosterParsed(Object source, RosterParsedEvent event);

}
