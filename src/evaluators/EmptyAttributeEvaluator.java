package evaluators;

import java.util.Collections;
import java.util.List;

import javafx.util.Pair;
import model.Attributes;

public class EmptyAttributeEvaluator<A extends Attributes>
	extends AttributeEvaluator<A>
{
	public EmptyAttributeEvaluator()
	{
		super("Empty");
	}

	@Override
	public double getQuality(A attributes)
	{
		return 0;
	}

	@Override
	protected List<Pair<String, Double>> createPairs(A attributes)
	{
		return Collections.emptyList();
	}

	@Override
	protected double attributeSum()
	{
		return 0;
	}
}
