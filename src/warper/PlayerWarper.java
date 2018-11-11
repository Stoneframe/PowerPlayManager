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

		double previousTotalRating = player.getAttributes().getTotalRating();

		for (int i = 1; i <= years; i++)
		{
			double nextTotalRating =
					playerEvaluator.calculateTotalRatingForAge(player, player.getAge() + i);

			int improvement = (int)(nextTotalRating - previousTotalRating);

			for (int j = 0; j < Math.abs(improvement); j++)
			{
				if (Integer.signum(improvement) > 0)
				{
					Attribute attribute = attributeEvaluator.getWorstAttribute(attributes);

					attribute.setRating(attribute.getRating() + 1);
				}
				else
				{
					Attribute attribute = attributeEvaluator.getBestAttribute(attributes);

					attribute.setRating(attribute.getRating() - 1);
				}
			}

			previousTotalRating = nextTotalRating;
		}

		return attributes;
	}

	protected abstract A copyAttributes(A original);
}
