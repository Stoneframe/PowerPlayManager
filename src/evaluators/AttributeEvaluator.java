package evaluators;

import model.Attribute;
import model.Attributes;

public abstract class AttributeEvaluator<A extends Attributes>
{
	private String name;

	public AttributeEvaluator(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public abstract boolean isMacroEvaluator();

	public abstract double getRating(A attributes);

	public abstract double getQuality(A attributes);

	public abstract Attribute getWorstAttribute(A attributes);

	public abstract Attribute getBestAttribute(A attributes);

	@Override
	public boolean equals(Object obj)
	{
		if (!(obj instanceof AttributeEvaluator<?>))
		{
			return false;
		}

		AttributeEvaluator<?> other = (AttributeEvaluator<?>)obj;

		return this.name.equals(other.name);
	}

	@Override
	public String toString()
	{
		return getName();
	}
}
