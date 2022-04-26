package formation;

import java.util.Objects;

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

	@Override
	public int hashCode()
	{
		return Objects.hash(name, attributeEvaluator, side, isIgnored);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof PositionTemplate<?>)
		{
			PositionTemplate<?> other = (PositionTemplate<?>)obj;

			return Objects.equals(this.name, other.name)
				&& Objects.equals(this.attributeEvaluator, other.attributeEvaluator)
				&& Objects.equals(this.side, other.side)
				&& Objects.equals(this.isIgnored, other.isIgnored);
		}

		return false;
	}
}
