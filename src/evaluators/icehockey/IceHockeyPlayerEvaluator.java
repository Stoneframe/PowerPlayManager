package evaluators.icehockey;

import java.util.List;

import evaluators.AttributeEvaluator;
import evaluators.PlayerEvaluator;
import model.icehockey.IceHockeyAttributes;

public class IceHockeyPlayerEvaluator extends PlayerEvaluator<IceHockeyAttributes>
{
	// private static final double A = -0.118, B = 5.1, C = 36.8, D = -1079;
	// private static final double A = -0.0723, B = 1.33, C = 140, D = -1964;
	private static final double A = -0.06723, B = 0.8775, C = 151.3, D = -2049;

	public IceHockeyPlayerEvaluator(
			List<AttributeEvaluator<IceHockeyAttributes>> attributeEvaluators)
	{
		super(attributeEvaluators, A, B, C, D);
	}
}
