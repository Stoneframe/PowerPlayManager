package warper.icehockey;

import evaluators.PlayerEvaluator;
import model.icehockey.IceHockeyAttributes;
import warper.PlayerWarper;

public class IceHockeyPlayerWarper
	extends PlayerWarper<IceHockeyAttributes>
{
	public IceHockeyPlayerWarper(PlayerEvaluator<IceHockeyAttributes> playerEvaluator)
	{
		super(playerEvaluator);
	}

	@Override
	protected IceHockeyAttributes copyAttributes(IceHockeyAttributes original)
	{
		return original.copy();
	}
}
