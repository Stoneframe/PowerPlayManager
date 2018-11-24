package evaluators.football;

import java.util.List;

import evaluators.AttributeEvaluator;
import evaluators.PlayerEvaluator;
import model.football.FootballAttributes;
import settings.SportSettings;

public class FootballPlayerEvaluator
	extends PlayerEvaluator<FootballAttributes>
{
	private static final double A = -0.001801, B = 0.01567, C = 1.351;

	public FootballPlayerEvaluator(
			SportSettings settings,
			List<AttributeEvaluator<FootballAttributes>> attributeEvaluators)
	{
		super(A, B, C, settings, 9, attributeEvaluators);
	}
}
