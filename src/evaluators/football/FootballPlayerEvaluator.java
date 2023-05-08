package evaluators.football;

import java.util.List;

import evaluators.AttributeEvaluator;
import evaluators.PlayerEvaluator;
import model.football.FootballAttributes;
import settings.SportSettings;

public class FootballPlayerEvaluator
	extends PlayerEvaluator<FootballAttributes>
{
	public FootballPlayerEvaluator(
		SportSettings settings,
		List<AttributeEvaluator<FootballAttributes>> attributeEvaluators)
	{
		super(settings, attributeEvaluators);
	}
}
