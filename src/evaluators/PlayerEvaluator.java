package evaluators;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import comparators.QualityEvaluatorComparator;
import comparators.RatingEvaluatorComparator;
import model.Attributes;
import model.Player;
import settings.Settings;

public class PlayerEvaluator<A extends Attributes>
{
	private static final int DAYS_PER_SEASON = 112;

	private double a, b, c;

	private Settings settings;

	private List<AttributeEvaluator<A>> attributeEvaluators;
	private FacilityEvaluator facilityEvaluator;

	public PlayerEvaluator(
			double a,
			double b,
			double c,
			Settings settings,
			FacilityEvaluator facilityEvaluator,
			List<AttributeEvaluator<A>> attributeEvaluators)
	{
		this.a = a;
		this.b = b;
		this.c = c;
		this.settings = settings;
		this.attributeEvaluators = attributeEvaluators;
		this.facilityEvaluator = facilityEvaluator;
	}

	public List<AttributeEvaluator<A>> getAttributeEvaluators(boolean ignoreMacroPosition)
	{
		return attributeEvaluators
			.stream()
			.filter(ev -> !(ignoreMacroPosition && ev.isMacroEvaluator()))
			.collect(Collectors.toList());
	}

	public void setAttributeEvaluators(List<AttributeEvaluator<A>> attributeEvaluators)
	{
		this.attributeEvaluators = attributeEvaluators;
	}

	public int getFacilityLevel()
	{
		return settings.getFacilityLevel();
	}

	public void setFacilityLevel(int facilityLevel)
	{
		settings.setFacilityLevel(facilityLevel);
	}

	public int getStaffEffectivness()
	{
		return settings.getStaffEffectivness();
	}

	public void setStaffEffectivness(int staffEffectivness)
	{
		settings.setStaffEffectivness(staffEffectivness);
	}

	public AttributeEvaluator<A> getBestEvaluatorByRating(A attributes)
	{
		return getAttributeEvaluators(true)
			.stream()
			.max(new RatingEvaluatorComparator<A>(attributes))
			.get();
	}

	public AttributeEvaluator<A> getBestEvaluatorByQuality(A attributes)
	{
		return getAttributeEvaluators(true)
			.stream()
			.max(new QualityEvaluatorComparator<A>(attributes))
			.get();
	}

	public PositionNameValue getBestPositionRating(Player<A> player)
	{
		AttributeEvaluator<A> evaluator = getBestEvaluatorByRating(player.getAttributes());

		return new PositionNameValue(
				evaluator.getName(),
				evaluator.getRating(player.getAttributes()));
	}

	public PositionNameValue getBestPositionQuality(Player<A> player)
	{
		AttributeEvaluator<A> evaluator = getBestEvaluatorByQuality(player.getAttributes());

		return new PositionNameValue(
				evaluator.getName(),
				evaluator.getQuality(player.getAttributes()));
	}

	public double calculateRatingForAge(Player<A> player, int age)
	{
		double modifier = getCLModifier(player.getCL(), player.getAge());

		return F(player.getAge(), age, x -> f(x * modifier))
				* DAYS_PER_SEASON
				* getTrainingValue(player, modifier)
				+ player.getAttributes().getTotalRating();
	}

	public double calculateHighestPossibleRating(Player<A> player)
	{
		double max = Double.MIN_VALUE;

		for (int i = player.getAge(); i < 40; i++)
		{
			double next = calculateRatingForAge(player, i);

			if (next > max)
			{
				max = next;
			}
			else
			{
				break;
			}
		}

		return max;
	}

	private double getTrainingValue(Player<A> player, double modifier)
	{
		double training = player.getTraining() != 0
				? player.getTraining()
				: calculatePlayerTraining(player);

		return training / f(player.getAge() * modifier);
	}

	private double calculatePlayerTraining(Player<A> player)
	{
		return getTrainingFacilityEffectivness() * getEstimatedPlayerTraining(player);
	}

	private double getTrainingFacilityEffectivness()
	{
		double facilityEffectivness = facilityEvaluator
			.getOverallEffectivness(getFacilityLevel(), getStaffEffectivness());

		return (40 + facilityEffectivness) / 145;
	}

	private double getEstimatedPlayerTraining(Player<A> player)
	{
		return -0.0228285 * player.getAge()
				+ 0.1290817 * player.getCL()
				+ 0.0093809 * getBestPositionQuality(player).getValue();
	}

	private static double getCLModifier(int cl, int age)
	{
		Function<Double, Double> calc = x -> 1.145 + (0.915 - 1.145) * x;

		switch (cl)
		{
			case 6:
				return calc.apply((age - 12d) / 6d);
			case 5:
				return calc.apply((age - 15d) / 7d);
			case 4:
				return calc.apply((age - 17d) / 9d);
			case 3:
				return calc.apply((age - 21d) / 9d);
			case 2:
				return calc.apply((age - 23d) / 10d);
			case 1:
				return calc.apply((age - 25d) / 10d);
			default:
				return 0;
		}
	}

	private double F(int a, int b, Function<Double, Double> f)
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

		for (double x = a + 1; x < b; x++)
		{
			sum += f.apply(x);
		}

		return invert ? -sum : sum;
	}

	private double f(double x)
	{
		return a * Math.pow(x, 2) + b * Math.pow(x, 1) + c;
	}
}