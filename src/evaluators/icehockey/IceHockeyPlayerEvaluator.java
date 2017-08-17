package evaluators.icehockey;

import java.util.List;

import evaluators.AttributeEvaluator;
import evaluators.PlayerEvaluator;
import model.icehockey.IceHockeyAttributes;

public class IceHockeyPlayerEvaluator extends PlayerEvaluator<IceHockeyAttributes>
{
	private static final double A = -0.118, B = 5.1, C = 36.8, D = -1079;

	public IceHockeyPlayerEvaluator(
			List<AttributeEvaluator<IceHockeyAttributes>> attributeEvaluators)
	{
		super(attributeEvaluators, A, B, C, D);
	}
}
