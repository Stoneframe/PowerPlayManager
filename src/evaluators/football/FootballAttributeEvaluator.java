package evaluators.football;

import java.util.Arrays;

import evaluators.MicroAttributeEvaluator;
import evaluators.Weight;
import evaluators.Weights;
import model.football.FootballAttributes;

public class FootballAttributeEvaluator
	extends MicroAttributeEvaluator<FootballAttributes>
{
	protected static final int PRIMARY = 100;
	protected static final int HIGH = 75;
	protected static final int MEDIUM = 50;
	protected static final int LOW = 30;

	public FootballAttributeEvaluator(
		String name,
		int goa,
		int def,
		int mid,
		int off,
		int sho,
		int pas,
		int tec,
		int spe,
		int hea)
	{
		super(
			name,
			new Weights(
				Arrays.asList(
					new Weight("Goa", goa),
					new Weight("Def", def),
					new Weight("Mid", mid),
					new Weight("Off", off),
					new Weight("Sho", sho),
					new Weight("Pas", pas),
					new Weight("Tec", tec),
					new Weight("Spe", spe),
					new Weight("Hea", hea))));
	}
}
