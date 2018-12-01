package evaluators;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import comparators.QualityEvaluatorComparator;
import comparators.RatingEvaluatorComparator;
import model.Attributes;
import model.Player;
import settings.SportSettings;

public class PlayerEvaluator<A extends Attributes>
{
	private static final int DAYS_PER_SEASON = 112;

	private static final double EXPERIENCE_FACTOR = 0.3;
	private static final double CHEMISTRY_FACTOR = 0.2;

	private double a, b;

	private SportSettings settings;

	private int numberOfAttributes;
	private List<AttributeEvaluator<A>> attributeEvaluators;

	public PlayerEvaluator(
			double a,
			double b,
			SportSettings settings,
			int numberOfAttributes,
			List<AttributeEvaluator<A>> attributeEvaluators)
	{
		this.a = a;
		this.b = b;
		this.settings = settings;
		this.numberOfAttributes = numberOfAttributes;
		this.attributeEvaluators = attributeEvaluators;
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

	public PositionNameValue getBestPositionForm(Player<A> player)
	{
		PositionNameValue bestPositionRating = getBestPositionRating(player);

		double rating = bestPositionRating.getValue();

		return new PositionNameValue(
				bestPositionRating.getName(),
				calculateFormForRating(player, rating));
	}

	public PositionNameValue getBestPositionQuality(Player<A> player)
	{
		AttributeEvaluator<A> evaluator = getBestEvaluatorByQuality(player.getAttributes());

		return new PositionNameValue(
				evaluator.getName(),
				evaluator.getQuality(player.getAttributes()));
	}

	public double calculateFormForRating(Player<A> player, double rating)
	{
		double energyBonus = player.getEnergy() / 100d;

		double chemistryBonus = CHEMISTRY_FACTOR * player.getChemistry() / 100d;
		double experienceBonus = EXPERIENCE_FACTOR * player.getExperience() / 100d;

		return rating * energyBonus * (1 + chemistryBonus + experienceBonus);
	}

	public double calculateTotalRatingForAge(Player<A> player, int age)
	{
		double modifier = getCLModifier(player);
		double quality = getBestPositionQuality(player).getValue();

		return F(player.getAge(), age, createPlayerCurve(modifier, quality))
				* DAYS_PER_SEASON
				+ player.getAttributes().getTotalRating();
	}

	public double calculateHighestPossibleRating(Player<A> player)
	{
		double max = Double.MIN_VALUE;

		for (int i = player.getAge(); i < 40; i++)
		{
			double next = calculateTotalRatingForAge(player, i);

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

	public double calculatePlayerTraining(Player<A> player)
	{
		double modifier = getCLModifier(player);
		double quality = getBestPositionQuality(player).getValue();

		return createPlayerCurve(modifier, quality).apply((double)player.getAge());
	}

	private Function<Double, Double> createPlayerCurve(double modifier, double quality)
	{
		return x -> getFacilityTraining() * f(x, modifier) * quality / 100;
	}

	private double getFacilityTraining()
	{
		return getFacilityEffectiveness() * 0.08 + 0.04;
	}

	private double getFacilityEffectiveness()
	{
		return (double)getFacilityLevel() * (1 + (double)getStaffEffectivness() / 200);
	}

	private double getCLModifier(Player<A> player)
	{
		return getCLModifier(player.getCL(), player.getAge());
	}

	private static double getCLModifier(int cl, int age)
	{
		final double upper = 1.303;
		final double lower = 0.8067;

		Function<Double, Double> calc = x -> upper + (lower - upper) * x;

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
			case 0:
				return calc.apply((age - 29d) / 11d);
			default:
				return 1;
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

		double sum = 0.5 * f.apply((double)a) + 0.5 * f.apply((double)b);

		for (double x = a + 1; x < b; x++)
		{
			sum += f.apply(x);
		}

		return invert ? -sum : sum;
	}

	private double f(double x, double modifier)
	{
		int zero = (int)Math.round(5 * (Math.sqrt(23248561) - 1081) / (1104 * modifier) + 15);

		if (x < zero)
		{
			return gtZeroCL((x - 15), modifier);
		}
		else
		{
			return eqZeroCL(x - zero);
		}
	}

	private double gtZeroCL(double x, double modifier)
	{
		return a * Math.pow(x * modifier, 2) + b * Math.pow(x * modifier, 1) + 1;
	}

	private double eqZeroCL(double x)
	{
		return Math.min((-0.06 * x + 0.06) * numberOfAttributes, 0);
	}
}