package gui.parse;

import java.util.EventListener;

public interface PlayersParsedListener extends EventListener
{
	public void playersParsed(Object source, PlayersParsedEvent event);

}
