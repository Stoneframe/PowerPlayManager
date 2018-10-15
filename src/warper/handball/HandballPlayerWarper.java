package warper.handball;

import evaluators.PlayerEvaluator;
import model.handball.HandballAttributes;
import warper.PlayerWarper;

public class HandballPlayerWarper
	extends PlayerWarper<HandballAttributes>
{
	public HandballPlayerWarper(PlayerEvaluator<HandballAttributes> playerEvaluator)
	{
		super(playerEvaluator);
	}

	@Override
	protected HandballAttributes copyAttributes(HandballAttributes original)
	{
		return original.copy();
	}
}
