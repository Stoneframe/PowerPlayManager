package gui;

import java.util.EventListener;

public interface PlayerSelectedListener extends EventListener
{
	public void playerSelected(Object source, PlayerSelectedEvent event);
}
