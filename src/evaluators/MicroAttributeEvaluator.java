package evaluators;

import model.Attribute;
import model.Attributes;

public abstract class MicroAttributeEvaluator<A extends Attributes>
	extends AttributeEvaluator<A>
{
	private Weights weights;

	public MicroAttributeEvaluator(String name, Weights weights)
	{
		super(name);

		this.weights = weights;
	}

	@Override
	public boolean isMacroEvaluator()
	{
		return false;
	}

	public double getRating(A attributes)
	{
		return weights.getRating(attributes);
	}

	public double getQuality(A attributes)
	{
		return weights.getQuality(attributes);
	}

	@Override
	public Attribute getWorstAttribute(A attributes)
	{
		return attributes
			.stream()
			.filter(a -> weights.getRating(a) > 0)
			.min(
				(a1, a2) -> Double.compare(
					weights.getRating(a1),
					weights.getRating(a2)))
			.get();
	}

	@Override
	public Attribute getBestAttribute(A attributes)
	{
		return attributes
				.stream()
				.filter(a -> weights.getRating(a) > 0)
				.max(
					(a1, a2) -> Double.compare(
						weights.getRating(a1),
						weights.getRating(a2)))
				.get();
	}
}
