package formation;

import evaluators.AttributeEvaluator;
import model.Attributes;
import model.Player;

public interface PlayerManipulator<A extends Attributes>
{
	public double manipulate(Player<A> player, AttributeEvaluator<A> attributeEvaluator);
}
