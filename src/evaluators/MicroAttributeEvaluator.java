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

	public String getNextTraining(A attributes)
	{
		Attribute attribute = attributes
			.stream()
			.min(
				(a1, a2) -> Double.compare(
					weights.getRating(a1),
					weights.getRating(a2)))
			.get();

		return attribute.getName();
	}
}
