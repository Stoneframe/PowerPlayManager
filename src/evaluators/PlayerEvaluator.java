package evaluators;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import comparators.QualityEvaluatorComparator;
import comparators.RatingEvaluatorComparator;
import model.Attribute;
import model.Attributes;
import model.Player;
import settings.SportSettings;

public class PlayerEvaluator<A extends Attributes>
{
	private static final int DAYS_PER_SEASON = 112;

	private static final double EXPERIENCE_FACTOR = 0.2;
	private static final double CHEMISTRY_FACTOR = 0.2;

	private SportSettings settings;

	private List<AttributeEvaluator<A>> attributeEvaluators;

	public PlayerEvaluator(
		SportSettings settings,
		List<AttributeEvaluator<A>> attributeEvaluators)
	{
		this.settings = settings;
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
		return F(player.getAge(), age, createPlayerCurve(player))
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
		return createPlayerCurve(player).apply(player.getAge());
	}

	public double calculatePlayerAttributeTrainingForYear(
		Player<A> player,
		Attribute attribute,
		int age)
	{
		TrainingCalculator<A> tc = new TrainingCalculator<>(
			getFacilityLevel(),
			getStaffEffectivness(),
			player,
			attribute);

		return tc.calc(age);
	}

	private Function<Integer, Double> createPlayerCurve(Player<A> player)
	{
		AttributeEvaluator<A> evaluator = getBestEvaluatorByRating(player.getAttributes());

		TrainingCalculator<A> tc = new TrainingCalculator<>(
			getFacilityLevel(),
			getStaffEffectivness(),
			player,
			evaluator);

		return x -> tc.calc(x);
	}

	private double F(int a, int b, Function<Integer, Double> f)
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

		double sum = 0.5 * f.apply(a) + 0.5 * f.apply(b);

		for (int x = a + 1; x < b; x++)
		{
			sum += f.apply(x);
		}

		return invert ? -sum : sum;
	}
}