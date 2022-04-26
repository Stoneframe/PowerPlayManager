package formation.manipulators;

import evaluators.AttributeEvaluator;
import evaluators.PlayerEvaluator;
import formation.PlayerManipulator;
import model.Attributes;
import model.Player;

public class PlayerEnergyManipulator<A extends Attributes>
	implements
		PlayerManipulator<A>
{
	private PlayerEvaluator<A> playerEvaluator;

	public PlayerEnergyManipulator(PlayerEvaluator<A> playerEvaluator)
	{
		this.playerEvaluator = playerEvaluator;
	}

	@Override
	public double manipulate(Player<A> player, AttributeEvaluator<A> attributeEvaluator)
	{
		double playerForm =
			playerEvaluator.calculateFormForRating(
				player,
				attributeEvaluator.getRating(player.getAttributes()));

		return playerForm * Math.pow((double)player.getEnergy() / 100, 5);
	}
}
