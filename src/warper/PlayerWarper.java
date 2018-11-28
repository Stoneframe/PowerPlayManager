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

			double improvement = (nextTotalRating - previousTotalRating);

			if (Integer.signum((int)improvement) > 0)
			{
				for (int j = 0; j < Math.abs(improvement); j++)
				{
					Attribute attribute = attributeEvaluator.getWorstAttribute(attributes);

					attribute.setRating(attribute.getRating() + 1);
				}
			}
			else
			{
				double decreasePerAttribute = improvement / attributes.getNumberOfAttributes();

				for (Attribute attribute : attributes)
				{
					attribute.addToRating(decreasePerAttribute);
				}
			}

			previousTotalRating = nextTotalRating;
		}

		return attributes;
	}

	protected abstract A copyAttributes(A original);
}
