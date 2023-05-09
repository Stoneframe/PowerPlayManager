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

		for (int year = 1; year <= years; year++)
		{
			for (int day = 1; day <= 112; day++)
			{
				Attribute attribute = attributeEvaluator.getWorstAttribute(attributes);

				double training = playerEvaluator.calculatePlayerAttributeTrainingForYear(
					player,
					attribute,
					player.getAge() + year);

				if (training > 0)
				{
					attribute.addToRating(training);
				}
				else
				{
					for (Attribute a : attributes)
					{
						a.addToRating(training);
					}
				}
			}
		}

		return attributes;
	}

	protected abstract A copyAttributes(A original);
}
