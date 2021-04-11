package evaluators;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.Attribute;
import model.Attributes;

public class Weights
{
	private Map<String, Weight> weights;

	public Weights(List<Weight> weights)
	{
		this.weights = weights.stream().collect(Collectors.toMap(w -> w.getName(), w -> w));
	}

	public int sum()
	{
		return weights.values().stream().mapToInt(w -> (int)w.getValue()).sum();
	}

	public double getRating(Attributes attributes)
	{
		return sum()
			* attributes
				.stream()
				.mapToDouble(a -> getWeight(a).getRating(a))
				.filter(r -> r > 0)
				.min()
				.getAsDouble();
	}

	public double getQuality(Attributes attributes)
	{
		return sum()
			/ attributes
				.stream()
				.mapToDouble(a -> getWeight(a).getQuality(a))
				.sum();
	}

	public double getRating(Attribute attribute)
	{
		return getWeight(attribute).getRating(attribute);
	}

	public double getQuality(Attribute attribute)
	{
		return getWeight(attribute).getQuality(attribute);
	}

	private Weight getWeight(Attribute attribute)
	{
		return weights.get(attribute.getShortName());
	}
}
