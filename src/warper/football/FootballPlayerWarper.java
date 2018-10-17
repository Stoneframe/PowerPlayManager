package warper.football;

import evaluators.PlayerEvaluator;
import model.football.FootballAttributes;
import warper.PlayerWarper;

public class FootballPlayerWarper
	extends
		PlayerWarper<FootballAttributes>
{
	public FootballPlayerWarper(PlayerEvaluator<FootballAttributes> playerEvaluator)
	{
		super(playerEvaluator);
	}

	@Override
	protected FootballAttributes copyAttributes(FootballAttributes original)
	{
		return original.copy();
	}
}
