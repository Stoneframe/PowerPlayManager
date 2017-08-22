package evaluators;

import java.util.List;

import comparators.QualityEvaluatorComparator;
import model.Attributes;
import model.Player;

public class PlayerEvaluator<A extends Attributes>
{
	private static int lowerBoundary(int cl)
	{
		return (int) (-3.5 * cl + 28.8);
	}

	private static int upperBoundary(int cl)
	{
		return (int) (-3.8 * cl + 41.2);
	}

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

	public double getRValue(Player<A> player)
	{
		return player.getAttributes().getTotalRating() / getGrowthValue(player.getAge())
				* getQuality(player.getAttributes());
	}

	public double getCLValue(Player<A> player)
	{
		int age = player.getAge();
		int cl = player.getCL();

		int lower = lowerBoundary(cl);
		int upper = upperBoundary(cl);

		return (double) (age - lower) / (double) (upper - lower);
	}

	public double getXValue(Player<A> player)
	{
		return getRValue(player) * getCLValue(player);
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
