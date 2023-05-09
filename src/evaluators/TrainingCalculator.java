package evaluators;

import model.Attribute;
import model.Attributes;
import model.Player;

public class TrainingCalculator<A extends Attributes>
{
	private static final double AGE_5 = -1.5864730710252552E-5;
	private static final double AGE_4 = 0.0018457010260159285;
	private static final double AGE_3 = -0.08375355573024544;
	private static final double AGE_2 = 1.8491229652134236;
	private static final double AGE_1 = -20.021937644105673;

	private static final double CL_5 = 0.03590926726882296;
	private static final double CL_4 = -0.5941853187031092;
	private static final double CL_3 = 3.5750319606349867;
	private static final double CL_2 = -9.719351510312881;
	private static final double CL_1 = 13.33888816752668;

	private static final double M = 82.54330843595311;

	private final int facility;
	private final int staff;

	private final int nbrOfAttributes;
	private final double quality;

	private final CareerLengthCurve clc;

	public TrainingCalculator(
		int facility,
		int staff,
		Player<A> player,
		AttributeEvaluator<A> evaluator)
	{
		this.facility = facility;
		this.staff = staff;

		this.nbrOfAttributes = player.getAttributes().getNumberOfAttributes();
		this.quality = evaluator.getQuality(player.getAttributes());

		clc = new CareerLengthCurve(player.getAge(), player.getCL());
	}

	public TrainingCalculator(
		int facility,
		int staff,
		Player<A> player,
		Attribute attribute)
	{
		this.facility = facility;
		this.staff = staff;

		this.nbrOfAttributes = 1;
		this.quality = attribute.getQuality();

		clc = new CareerLengthCurve(player.getAge(), player.getCL());
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
