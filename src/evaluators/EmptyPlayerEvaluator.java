package evaluators;

import model.Attributes;

public class EmptyPlayerEvaluator<A extends Attributes> extends PlayerEvaluator<A>
{
	public EmptyPlayerEvaluator()
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
