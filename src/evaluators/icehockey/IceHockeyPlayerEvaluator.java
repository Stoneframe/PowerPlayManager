package evaluators.icehockey;

import java.util.List;

import evaluators.AttributeEvaluator;
import evaluators.PlayerEvaluator;
import model.icehockey.IceHockeyAttributes;
import settings.SportSettings;

public class IceHockeyPlayerEvaluator
	extends PlayerEvaluator<IceHockeyAttributes>
{
	private static final double A = -0.002208, B = -0.02162, C = 1;

	public IceHockeyPlayerEvaluator(
		SportSettings settings,
		List<AttributeEvaluator<IceHockeyAttributes>> attributeEvaluators)
	{
		super(A, B, C, settings, 7, attributeEvaluators);
	}
}
