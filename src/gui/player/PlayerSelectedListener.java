package gui.player;

import java.util.EventListener;

import model.Attributes;

public interface PlayerSelectedListener<A extends Attributes>
		extends
		EventListener
{
	public void playerSelected(Object source, PlayerSelectedEvent<A> event);
}
