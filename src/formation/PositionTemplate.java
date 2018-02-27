package formation;

import evaluators.AttributeEvaluator;
import model.Attributes;
import model.Side;

public class PositionTemplate<A extends Attributes>
{
	private String name;

	private AttributeEvaluator<A> attributeEvaluator;

	private Side side;

	private boolean isIgnored;

	public PositionTemplate(String name, AttributeEvaluator<A> attributeEvaluator, Side side)
	{
		this.name = name;
		this.attributeEvaluator = attributeEvaluator;
		this.side = side;
	}

	public PositionTemplate(
			String name,
			AttributeEvaluator<A> attributeEvaluator,
			Side side,
			boolean isIgnored)
	{
		this(name, attributeEvaluator, side);

		this.isIgnored = isIgnored;
	}

	public String getName()
	{
		return name;
	}

	public AttributeEvaluator<A> getAttributeEvaluator()
	{
		return attributeEvaluator;
	}

	public Side getSide()
	{
		return side;
	}

	public boolean isIgnored()
	{
		return isIgnored;
	}
}
