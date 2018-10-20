package warper;

import evaluators.AttributeEvaluator;
import evaluators.PlayerEvaluator;
import model.Attribute;
import model.Attributes;
import model.Player;

public abstract class PlayerWarper<A extends Attributes>
{
	private PlayerEvaluator<A> playerEvaluator;

	public PlayerWarper(PlayerEvaluator<A> playerEvaluator)
	{
		this.playerEvaluator = playerEvaluator;
	}

	public A warp(Player<A> player, AttributeEvaluator<A> attributeEvaluator, int years)
	{
		A attributes = copyAttributes(player.getAttributes());

		int improvement = calculateImprovement(player, years);

		for (int i = 0; i < Math.abs(improvement); i++)
		{
			Attribute attribute = attributeEvaluator.getNextTraining(attributes);

			attribute.setRating(attribute.getRating() + Integer.signum(improvement));
		}

		return attributes;
	}

	protected abstract A copyAttributes(A original);

	private int calculateImprovement(Player<A> player, int years)
	{
		double futureRating = playerEvaluator
			.calculateRatingForAge(player, player.getAge() + years);

		double currentRating = player.getAttributes().getTotalRating();

		return (int)(futureRating - currentRating);
	}
}
