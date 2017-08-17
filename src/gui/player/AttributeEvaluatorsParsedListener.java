package gui.player;

import java.util.EventListener;

import model.Attributes;

public interface AttributeEvaluatorsParsedListener<A extends Attributes> extends EventListener
{
	public void attributeEvaluatorsParsed(
			Object source,
			AttributeEvaluatorsParsedEvent<A> event);
}
