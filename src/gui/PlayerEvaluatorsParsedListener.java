package gui;

import java.util.EventListener;

public interface PlayerEvaluatorsParsedListener extends EventListener
{
	public void playerEvaluatorsParsed(
			Object source,
			PlayerEvaluatorsParsedEvent event);
}
