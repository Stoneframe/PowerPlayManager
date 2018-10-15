package formation.manipulators;

import evaluators.AttributeEvaluator;
import formation.PlayerManipulator;
import model.Attributes;
import model.Player;
import warper.PlayerWarper;

public class PlayerWarpManipulator<A extends Attributes>
	implements
		PlayerManipulator<A>
{
	private PlayerWarper<A> playerWarper;
	
	private int years;
	
	public PlayerWarpManipulator(PlayerWarper<A> playerWarper, int years)
	{
		this.playerWarper = playerWarper;
		this.years = years;
	}

	@Override
	public double manipulate(Player<A> player, AttributeEvaluator<A> attributeEvaluator)
	{
		A attributes = playerWarper.warp(player, years);
		
		return attributeEvaluator.getRating(attributes);
	}
}
