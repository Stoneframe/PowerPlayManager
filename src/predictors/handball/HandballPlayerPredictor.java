package predictors.handball;

import java.util.List;

import evaluators.AttributeEvaluator;
import evaluators.PlayerEvaluator;
import evaluators.handball.HandballAttributeEvaluator;
import model.Player;
import model.handball.HandballAttributes;
import predictors.PlayerPredictor;

public class HandballPlayerPredictor
	extends PlayerPredictor<HandballAttributes>
{
	public HandballPlayerPredictor(
			PlayerEvaluator<HandballAttributes> playerEvaluator,
			List<AttributeEvaluator<HandballAttributes>> attributeEvaluators)
	{
		super(playerEvaluator, attributeEvaluators);
	}

	@Override
	public void predictPlayerAttributes(Player<HandballAttributes> player, int yearsIntoFuture)
	{
		int ratingImprovement = getRatingImprovment(player, yearsIntoFuture, playerEvaluator);

		HandballAttributeEvaluator bestAttributeEvaluator =
				getBestAttributeEvaluator(player, playerEvaluator, attributeEvaluators);

		player.setAge(player.getAge() + yearsIntoFuture);

		HandballAttributes attributes = player.getAttributes();

		attributes.setGoa(
			attributes.getGoa() + (int)(ratingImprovement * bestAttributeEvaluator.getGoaWeight()));
		attributes.setFip(
			attributes.getFip() + (int)(ratingImprovement * bestAttributeEvaluator.getFipWeight()));
		attributes.setSho(
			attributes.getSho() + (int)(ratingImprovement * bestAttributeEvaluator.getShoWeight()));
		attributes.setBlk(
			attributes.getBlk() + (int)(ratingImprovement * bestAttributeEvaluator.getBlkWeight()));
		attributes.setPas(
			attributes.getPas() + (int)(ratingImprovement * bestAttributeEvaluator.getPasWeight()));
		attributes.setTec(
			attributes.getTec() + (int)(ratingImprovement * bestAttributeEvaluator.getTecWeight()));
		attributes.setSpe(
			attributes.getSpe() + (int)(ratingImprovement * bestAttributeEvaluator.getSpeWeight()));
		attributes.setAgr(
			attributes.getAgr() + (int)(ratingImprovement * bestAttributeEvaluator.getAgrWeight()));
	}

	private int getRatingImprovment(
			Player<HandballAttributes> player,
			int yearsIntoFuture,
			PlayerEvaluator<HandballAttributes> playerEvaluator)
	{
		int futureRating = (int)playerEvaluator
				.calculateRatingForAge(player, player.getAge() + yearsIntoFuture);

		return futureRating - player.getAttributes().getTotalRating();
	}

	private HandballAttributeEvaluator getBestAttributeEvaluator(
			Player<HandballAttributes> player,
			PlayerEvaluator<HandballAttributes> playerEvaluator,
			List<AttributeEvaluator<HandballAttributes>> attributeEvaluators)
	{
		return (HandballAttributeEvaluator)attributeEvaluators
				.stream()
				.filter(
					e -> e.getName().equals(
						playerEvaluator.getBestPositionQuality(player).getName()))
				.findFirst()
				.get();
	}
}
