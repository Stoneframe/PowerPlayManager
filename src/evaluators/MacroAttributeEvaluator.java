package evaluators;

import java.util.List;

import comparators.QualityEvaluatorComparator;
import comparators.RatingEvaluatorComparator;
import model.Attribute;
import model.Attributes;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class MacroAttributeEvaluator<A extends Attributes>
	extends AttributeEvaluator<A>
{
	private List<AttributeEvaluator<A>> attributeEvaluators;

	public MacroAttributeEvaluator(String name, List<AttributeEvaluator<A>> attributeEvaluators)
	{
		super(name);

		this.attributeEvaluators = attributeEvaluators;
	}

	@Override
	public boolean isMacroEvaluator()
	{
		return true;
	}

	@Override
	public double getRating(A attributes)
	{
		return attributeEvaluators
			.stream()
			.max(new RatingEvaluatorComparator<A>(attributes)::compare)
			.get()
			.getRating(attributes);
	}

	@Override
	public double getQuality(A attributes)
	{
		return attributeEvaluators
			.stream()
			.max(new QualityEvaluatorComparator<A>(attributes)::compare)
			.get()
			.getQuality(attributes);
	}

	@Override
	public Attribute getWorstAttribute(A attributes)
	{
		throw new NotImplementedException();
	}

	@Override
	public Attribute getBestAttribute(A attributes)
	{
		throw new NotImplementedException();
	}
}
