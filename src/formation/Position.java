package formation;

import evaluators.AttributeEvaluator;
import model.Attributes;
import model.Side;

public class Position<A extends Attributes>
{
	private AttributeEvaluator<A> attributeEvaluator;

	private Side side;

	public Position(AttributeEvaluator<A> attributeEvaluator, Side side)
	{
		this.attributeEvaluator = attributeEvaluator;
		this.side = side;
	}

	public AttributeEvaluator<A> getAttributeEvaluator()
	{
		return attributeEvaluator;
	}

	public Side getSide()
	{
		return side;
	}
}
