package evaluators;

import model.Attributes;
import model.Player;

public class PlayerTrainingCalculator<A extends Attributes>
{
	private final AttributeTrainingCalculator<A> attributeTrainingCalculator;

	private final int nbrOfAttributes;

	public PlayerTrainingCalculator(
		int facility,
		int staff,
		Player<A> player,
		AttributeEvaluator<A> attributeEvaluator)
	{
		nbrOfAttributes = player.getAttributes().getNumberOfAttributes();

		attributeTrainingCalculator = new AttributeTrainingCalculator<>(
			facility,
			staff,
			player.getAge(),
			player.getCL(),
			attributeEvaluator.getQuality(player.getAttributes()));
	}

	public double calc(int age)
	{
		double training = attributeTrainingCalculator.calc(age);

		if (training > 0)
		{
			return training;
		}

		return training * nbrOfAttributes;
	}
}
