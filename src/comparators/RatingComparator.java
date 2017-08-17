package comparators;

import java.util.Comparator;

import evaluators.AttributeEvaluator;
import model.Attributes;
import model.Player;

public class RatingComparator<A extends Attributes>
		implements
		Comparator<Player<A>>
{
	private AttributeEvaluator<A> evaluator;

	public RatingComparator(AttributeEvaluator<A> evaluator)
	{
		this.evaluator = evaluator;
	}

	@Override
	public int compare(Player<A> o1, Player<A> o2)
	{
		return Double.compare(
			evaluator.getRating(o1.getAttributes()),
			evaluator.getRating(o2.getAttributes()));
	}
}
