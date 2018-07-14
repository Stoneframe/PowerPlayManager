package evaluators.handball;

import java.util.List;

import evaluators.AttributeEvaluator;
import evaluators.PlayerEvaluator;
import model.handball.HandballAttributes;
import settings.SportSettings;

public class HandballPlayerEvaluator
	extends PlayerEvaluator<HandballAttributes>
{
	private static final double A = -0.001801, B = 0.01567, C = 1.351;

	public HandballPlayerEvaluator(
			SportSettings settings,
			List<AttributeEvaluator<HandballAttributes>> attributeEvaluators)
	{
		super(A, B, C, settings, attributeEvaluators);
	}
}
