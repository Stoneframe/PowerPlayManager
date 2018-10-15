package formation.manipulators;

import evaluators.AttributeEvaluator;
import formation.PlayerManipulator;
import model.Attributes;
import model.Player;

public class PlayerNoneManipulator<A extends Attributes>
	implements
		PlayerManipulator<A>
{
	@Override
	public double manipulate(Player<A> player, AttributeEvaluator<A> attributeEvaluator)
	{
		return attributeEvaluator.getRating(player.getAttributes());
	}
}
