package evaluators.handball;

import java.util.List;

import evaluators.AttributeEvaluator;
import evaluators.PlayerEvaluator;
import model.handball.HandballAttributes;
import settings.SportSettings;

public class HandballPlayerEvaluator
	extends PlayerEvaluator<HandballAttributes>
{
	private static final double A = -0.002208, B = -0.02162;

	public HandballPlayerEvaluator(
			SportSettings settings,
			List<AttributeEvaluator<HandballAttributes>> attributeEvaluators)
	{
		super(A, B, settings, attributeEvaluators);
	}
}
