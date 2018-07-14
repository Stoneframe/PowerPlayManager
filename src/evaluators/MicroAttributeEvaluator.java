package evaluators;

import java.util.List;

import javafx.util.Pair;
import model.Attributes;

public abstract class MicroAttributeEvaluator<A extends Attributes>
	extends AttributeEvaluator<A>
{
	public MicroAttributeEvaluator(String name)
	{
		super(name);
	}

	@Override
	public boolean isMacroEvaluator()
	{
		return false;
	}

	public double getRating(A attributes)
	{
		List<Pair<String, Double>> pairs = createPairs(attributes);

		double value = pairs
			.stream()
			.min((o1, o2) -> Double.compare(o1.getValue(), o2.getValue()))
			.get()
			.getValue();

		return attributeSum() * value;
	}

	public abstract double getQuality(A attributes);

	public String getNextTraining(A attributes)
	{
		List<Pair<String, Double>> pairs = createPairs(attributes);

		return pairs
			.stream()
			.min((o1, o2) -> Double.compare(o1.getValue(), o2.getValue()))
			.get()
			.getKey();
	}

	protected abstract double attributeSum();

	protected abstract List<Pair<String, Double>> createPairs(A attributes);

	protected Pair<String, Double> createPair(String name, double rating, double weight)
	{
		return new Pair<String, Double>(
				name,
				rating / (weight != 0 ? weight : 0.0000000001));
	}
}
