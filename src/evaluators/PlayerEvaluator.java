package evaluators;

import model.Attributes;
import model.Player;
import util.AbstractModelObject;

public class PlayerEvaluator<A extends Attributes> extends AbstractModelObject
{
	private static final int DAYS_PER_SEASON = 112;

	private double a, b, c, age15Rating;

	public PlayerEvaluator(
			double a,
			double b,
			double c,
			double age15Rating)
	{
		this.a = a;
		this.b = b;
		this.c = c;
		this.age15Rating = age15Rating;
	}

	public void setCoeffs(double a, double b, double c)
	{
		this.a = a;
		this.b = b;
		this.c = c;
		firePropertyChanged("Coeffs", new double[]
		{
				a, b, c
		});
	}

	public void setAge15Rating(int age15Rating)
	{
		this.age15Rating = age15Rating;
	}

	public double getCLValue(Player<A> player)
	{
		int age = player.getAge();
		int cl = player.getCL();

		int lower = lowerBoundary(cl);
		int upper = upperBoundary(cl);

		return (double) (age - lower) / (double) (upper - lower);
	}

	public double getRatingValue(Player<A> player)
	{
		return player.getAttributes().getTotalRating() / getGrowthValue(player.getAge());
	}

	public double getTrainingValue(Player<A> player)
	{
		return player.getTraining() / f(player.getAge());
	}

	public double calculateRatingForAge(Player<A> player, int age)
	{
		return F(player.getAge(), age) * DAYS_PER_SEASON * getTrainingValue(player)
				+ player.getAttributes().getTotalRating();
	}

	private double getGrowthValue(int x)
	{
		return F(15, x) * DAYS_PER_SEASON + age15Rating;
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

	private static int lowerBoundary(int cl)
	{
		return (int) (-3.5 * cl + 28.8);
	}

	private static int upperBoundary(int cl)
	{
		return (int) (-3.8 * cl + 41.2);
	}
}
