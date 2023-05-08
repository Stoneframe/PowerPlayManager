package evaluators.handball;

import java.util.List;

import evaluators.AttributeEvaluator;
import evaluators.PlayerEvaluator;
import model.handball.HandballAttributes;
import settings.SportSettings;

public class HandballPlayerEvaluator
	extends PlayerEvaluator<HandballAttributes>
{
	public HandballPlayerEvaluator(
		SportSettings settings,
		List<AttributeEvaluator<HandballAttributes>> attributeEvaluators)
	{
		super(settings, attributeEvaluators);
	}
}
