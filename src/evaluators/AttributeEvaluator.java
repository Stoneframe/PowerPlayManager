package evaluators;

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

	public abstract double getRating(A attributes);

	public abstract double getQuality(A attributes);

	@Override
	public String toString()
	{
		return getName();
	}
}
