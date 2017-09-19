package evaluators;

import java.util.List;

import comparators.QualityEvaluatorComparator;
import comparators.RatingEvaluatorComparator;
import model.Attributes;
import model.Player;

public class PlayerEvaluator<A extends Attributes>
{
	private static final int DAYS_PER_SEASON = 112;

	private double a, b, c;

	private int facilityLevel = 1;
	private int staffEffectivness = 0;

	private List<AttributeEvaluator<A>> attributeEvaluators;

	private FacilityEvaluator facilityEvaluator;

	public PlayerEvaluator(
			double a,
			double b,
			double c,
			List<AttributeEvaluator<A>> attributeEvaluators,
			FacilityEvaluator facilityEvaluator)
	{
		this.a = a;
		this.b = b;
		this.c = c;
		this.attributeEvaluators = attributeEvaluators;
		this.facilityEvaluator = facilityEvaluator;
	}

	public int getFacilityLevel()
	{
		return facilityLevel;
	}

	public void setFacilityLevel(int facilityLevel)
	{
		this.facilityLevel = facilityLevel;
	}

	public int getStaffEffectivness()
	{
		return staffEffectivness;
	}

	public void setStaffEffectivness(int staffEffectivness)
	{
		this.staffEffectivness = staffEffectivness;
	}

	public void setAttributeEvaluators(List<AttributeEvaluator<A>> attributeEvaluators)
	{
		this.attributeEvaluators = attributeEvaluators;
	}

	public PositionNameValue getBestPositionRating(Player<A> player)
	{
		AttributeEvaluator<A> evaluator = attributeEvaluators
				.stream()
				.max((a, b) -> new RatingEvaluatorComparator<A>(
						player.getAttributes()).compare(a, b))
				.get();

		return new PositionNameValue(
				evaluator.getName(),
				evaluator.getRating(player.getAttributes()));
	}

	public PositionNameValue getBestPositionQuality(Player<A> player)
	{
		AttributeEvaluator<A> evaluator = attributeEvaluators
				.stream()
				.max((a, b) -> new QualityEvaluatorComparator<A>(
						player.getAttributes()).compare(a, b))
				.get();

		return new PositionNameValue(
				evaluator.getName(),
				evaluator.getQuality(player.getAttributes()));
	}

	public double calculateRatingForAge(Player<A> player, int age)
	{
		return F(player.getAge(), age) * DAYS_PER_SEASON * getTrainingValue(player)
				+ player.getAttributes().getTotalRating();
	}

	public double getTrainingValue(Player<A> player)
	{
		double training = player.getTraining() != 0
				? player.getTraining()
				: calculateTraning(player);

		return training / f(player.getAge());
	}

	private double calculateTraning(Player<A> player)
	{
		return getTrainingFacilityEffectivness() * getEstimatedPlayerTraining(player);
	}

	private double getTrainingFacilityEffectivness()
	{
		double facilityEffectivness =
				facilityEvaluator.getOverallEffectivness(
					facilityLevel,
					staffEffectivness);

		return (40 + facilityEffectivness) / 145;
	}

	private double getEstimatedPlayerTraining(Player<A> player)
	{
		return -0.02278 * player.getAge()
				+ 0.1291 * player.getCL()
				- 0.0003325 * player.getAttributes().getAverageQuality()
				+ 0.009671 * getBestPositionQuality(player).getValue();
	}

	private double f(int x)
	{
		return a * Math.pow(x, 2) + b * Math.pow(x, 1) + c;
	}

	private double F(int a, int b)
	{
		boolean invert = false;

		if (a == b)
		{
			return 0;
		}
		else if (b < a)
		{
			invert = true;

			int t = a;
			a = b;
			b = t;
		}

		double sum = 0.5 * f(a) + 0.5 * f(b);

		for (int x = a + 1; x < b; x++)
		{
			sum += f(x);
		}

		return invert ? -sum : sum;
	}
}
