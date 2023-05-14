package warper;

import calendar.Calendar;
import evaluators.AttributeEvaluator;
import evaluators.PlayerEvaluator;
import model.Attribute;
import model.Attributes;
import model.Player;

public abstract class PlayerWarper<A extends Attributes>
{
	private final PlayerEvaluator<A> playerEvaluator;

	private final Calendar calendar;

	public PlayerWarper(PlayerEvaluator<A> playerEvaluator)
	{
		this.playerEvaluator = playerEvaluator;

		calendar = playerEvaluator.getCalendar();
	}

	public A warp(Player<A> player, AttributeEvaluator<A> attributeEvaluator, int years)
	{
		A attributes = copyAttributes(player.getAttributes());

		for (int year = 0; year < years; year++)
		{
			int daysInSeason = calendar.getDaysRemainingInSeasons(calendar.getSeason() + year);

			for (int day = 1; day <= daysInSeason; day++)
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
