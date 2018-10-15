package formation.manipulators;

import evaluators.AttributeEvaluator;
import evaluators.PlayerEvaluator;
import formation.PlayerManipulator;
import model.Attributes;
import model.Player;

public class PlayerFormManipulator<A extends Attributes>
	implements
		PlayerManipulator<A>
{
	private PlayerEvaluator<A> playerEvaluator;

	public PlayerFormManipulator(PlayerEvaluator<A> playerEvaluator)
	{
		this.playerEvaluator = playerEvaluator;
	}

	@Override
	public double manipulate(Player<A> player, AttributeEvaluator<A> attributeEvaluator)
	{
		return playerEvaluator.calculateFormForRating(
			player,
			attributeEvaluator.getRating(player.getAttributes()));
	}
}
