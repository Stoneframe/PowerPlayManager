package gui.player;

import java.util.EventListener;

import model.Attributes;

public interface PlayerEvaluatorsParsedListener<A extends Attributes>
		extends EventListener
{
	public void playerEvaluatorsParsed(
			Object source,
			PlayerEvaluatorsParsedEvent<A> event);
}
