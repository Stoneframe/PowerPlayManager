package evaluators.handball;

import java.util.Arrays;

import evaluators.MicroAttributeEvaluator;
import evaluators.Weight;
import evaluators.Weights;
import model.handball.HandballAttributes;

public class HandballAttributeEvaluator
	extends MicroAttributeEvaluator<HandballAttributes>
{
	public HandballAttributeEvaluator(
		String name,
		int goa,
		int fip,
		int sho,
		int blk,
		int pas,
		int tec,
		int spe,
		int agr)
	{
		super(
			name,
			new Weights(
				Arrays.asList(
					new Weight("Goa", goa),
					new Weight("Fip", fip),
					new Weight("Sho", sho),
					new Weight("Blk", blk),
					new Weight("Pas", pas),
					new Weight("Tec", tec),
					new Weight("Spe", spe),
					new Weight("Agr", agr))));
	}
}
