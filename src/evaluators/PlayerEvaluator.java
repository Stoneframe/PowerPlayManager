package evaluators;

import java.util.List;

import comparators.QualityEvaluatorComparator;
import model.Attributes;
import model.Player;

public class PlayerEvaluator<A extends Attributes>
{
	private List<AttributeEvaluator<A>> attributeEvaluators;
	private double a, b, c, d;

	public PlayerEvaluator(
			List<AttributeEvaluator<A>> attributeEvaluators,
			double a,
			double b,
			double c,
			double d)
	{
		this.attributeEvaluators = attributeEvaluators;
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}

	public double getValue(Player<A> player)
	{
		return player.getAttributes().getTotalRating() / getGrowthValue(player.getAge())
				* getQuality(player.getAttributes());
	}

	private double getGrowthValue(int x)
	{
		return a * Math.pow(x, 3) + b * Math.pow(x, 2) + c * Math.pow(x, 1) + d;
	}

	private double getQuality(A attributes)
	{
		return attributeEvaluators
				.stream()
				.max((a, b) -> new QualityEvaluatorComparator<A>(attributes).compare(a, b))
				.map(e -> e.getQuality(attributes))
				.get();
	}
}
