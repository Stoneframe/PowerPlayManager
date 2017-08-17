package evaluators;

import model.Attributes;

public class EmptyAttributeEvaluator<A extends Attributes> extends AttributeEvaluator<A>
{
	public EmptyAttributeEvaluator()
	{
		super("Empty");
	}

	@Override
	public double getRating(A attributes)
	{
		return 0;
	}

	@Override
	public double getQuality(A attributes)
	{
		return 0;
	}
}
