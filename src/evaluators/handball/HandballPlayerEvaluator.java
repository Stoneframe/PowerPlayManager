package evaluators.handball;

import java.util.List;

import evaluators.AttributeEvaluator;
import evaluators.FacilityEvaluator;
import evaluators.PlayerEvaluator;
import model.handball.HandballAttributes;
import settings.Settings;

public class HandballPlayerEvaluator
	extends PlayerEvaluator<HandballAttributes>
{
	private static final double A = -0.001801, B = 0.01567, C = 1.351;

	public HandballPlayerEvaluator(
			Settings settings,
			FacilityEvaluator facilityEvaluator,
			List<AttributeEvaluator<HandballAttributes>> attributeEvaluators)
	{
		super(A, B, C, settings, facilityEvaluator, attributeEvaluators);
	}
}
