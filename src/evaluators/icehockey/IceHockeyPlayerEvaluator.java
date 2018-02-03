package evaluators.icehockey;

import java.util.List;

import evaluators.AttributeEvaluator;
import evaluators.FacilityEvaluator;
import evaluators.PlayerEvaluator;
import model.icehockey.IceHockeyAttributes;
import settings.Settings;

public class IceHockeyPlayerEvaluator
	extends PlayerEvaluator<IceHockeyAttributes>
{
	private static final double A = -0.001801, B = 0.01567, C = 1.351;

	public IceHockeyPlayerEvaluator(
			Settings settings,
			FacilityEvaluator facilityEvaluator,
			List<AttributeEvaluator<IceHockeyAttributes>> attributeEvaluators)
	{
		super(A, B, C, settings, facilityEvaluator, attributeEvaluators);
	}
}
