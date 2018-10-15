package evaluators.icehockey;

import java.util.Arrays;

import evaluators.MicroAttributeEvaluator;
import evaluators.Weight;
import evaluators.Weights;
import model.icehockey.IceHockeyAttributes;

public class IceHockeyAttributeEvaluator
	extends MicroAttributeEvaluator<IceHockeyAttributes>
{
	public IceHockeyAttributeEvaluator(
			String name,
			int goa,
			int def,
			int off,
			int sho,
			int pas,
			int tec,
			int agr)
	{
		super(
			name,
			new Weights(
					Arrays.asList(
						new Weight("Goa", goa),
						new Weight("Def", def),
						new Weight("Off", off),
						new Weight("Sho", sho),
						new Weight("Pas", pas),
						new Weight("Tec", tec),
						new Weight("Agr", agr))));
	}
}
