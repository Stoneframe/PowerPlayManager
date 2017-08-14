package gui.player;

import java.util.EventListener;

import model.Attributes;

public interface PlayersParsedListener<A extends Attributes>
		extends EventListener
{
	public void playersParsed(Object source, PlayersParsedEvent<A> event);

}
