package evaluators;

import model.Attributes;
import model.Player;

public class TrainingCalculator<A extends Attributes>
{
	private static final double AGE_5 = -0.0000152640;
	private static final double AGE_4 = 0.0017800558;
	private static final double AGE_3 = -0.0809341322;
	private static final double AGE_2 = 1.7894207375;
	private static final double AGE_1 = -19.3947514251;

	private static final double CL_5 = 0.0360213161;
	private static final double CL_4 = -0.5963990250;
	private static final double CL_3 = 3.5917573254;
	private static final double CL_2 = -9.7765269754;
	private static final double CL_1 = 13.4214917013;

	private static final double M = 79.8665242425;

	private final int facility;
	private final int staff;

	private final CareerLengthCurve clc;

	private final int nbrOfAttributes;
	private final double quality;

	public TrainingCalculator(
		int facility,
		int staff,
		Player<A> player,
		AttributeEvaluator<A> evaluator)
	{
		this.facility = facility;
		this.staff = staff;

		clc = new CareerLengthCurve(player.getAge(), player.getCL());

		nbrOfAttributes = player.getAttributes().getNumberOfAttributes();
		quality = evaluator.getQuality(player.getAttributes());
	}

	public double calc(int age)
	{
		int cl = clc.get(age);

		if (cl > 0)
		{
			return Math.max(sf(facility, staff) * p(age, cl, quality) / 100, 0);
		}
		else
		{
			return Math.min((-0.06 * (age - clc.getEnd()) + 0.06) * nbrOfAttributes, 0);
		}
	}

	private double sf(double facility, double staff)
	{
		return facility * (1 + staff / 200);
	}

	private double p(int age, int cl, double quality)
	{
		return t(age, cl) * (quality / 100);
	}

	private double t(int age, int cl)
	{
		/* @formatter:off */
		return
			AGE_5 * Math.pow(age, 5) +
			AGE_4 * Math.pow(age, 4) +
			AGE_3 * Math.pow(age, 3) +
			AGE_2 * Math.pow(age, 2) +
			AGE_1 * Math.pow(age, 1) +
			CL_5 * Math.pow(cl, 5) +
			CL_4 * Math.pow(cl, 4) +
			CL_3 * Math.pow(cl, 3) +
			CL_2 * Math.pow(cl, 2) +
			CL_1 * Math.pow(cl, 1) +
			M;
		/* @formatter:on */
	}
}
