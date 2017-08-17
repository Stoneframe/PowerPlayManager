package gui.player;

import java.util.EventObject;
import java.util.List;

import evaluators.AttributeEvaluator;
import model.Attributes;

public class AttributeEvaluatorsParsedEvent<A extends Attributes>
		extends EventObject
{
	private static final long serialVersionUID = 5087168124511401024L;

	private List<AttributeEvaluator<A>> attributeEvaluators;

	public AttributeEvaluatorsParsedEvent(
			Object source,
			List<AttributeEvaluator<A>> attributeEvaluators)
	{
		super(source);

		this.attributeEvaluators = attributeEvaluators;
	}

	public List<AttributeEvaluator<A>> getAttributeEvaluators()
	{
		return attributeEvaluators;
	}
}
