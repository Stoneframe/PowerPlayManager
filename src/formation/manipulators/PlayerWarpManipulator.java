package formation.manipulators;

import evaluators.AttributeEvaluator;
import evaluators.PlayerEvaluator;
import formation.PlayerManipulator;
import model.Attributes;
import model.Player;
import warper.PlayerWarper;

public class PlayerWarpManipulator<A extends Attributes>
	implements
		PlayerManipulator<A>
{
	private final PlayerEvaluator<A> playerEvaluator;
	private final PlayerWarper<A> playerWarper;

	private final int years;

	public PlayerWarpManipulator(
		PlayerEvaluator<A> playerEvaluator,
		PlayerWarper<A> playerWarper,
		int years)
	{
		this.playerEvaluator = playerEvaluator;
		this.playerWarper = playerWarper;
		this.years = years;
	}

	@Override
	public double manipulate(Player<A> player, AttributeEvaluator<A> attributeEvaluator)
	{
		A attributes = playerWarper.warp(
			player,
			playerEvaluator.getBestEvaluatorByRating(player.getAttributes()),
			years);

		return attributeEvaluator.getRating(attributes);
	}
}
