package gui.player;

import comparators.RatingEvaluatorComparator;
import evaluators.PlayerEvaluator;
import model.Attributes;

public class PositionSuggestionPanel<A extends Attributes> extends
		SuggestionPanel<A>
{
	private static final long serialVersionUID = -1128283837238130849L;

	public PositionSuggestionPanel()
	{
		super("Position");
	}

	@Override
	protected int compare(
			A attributes,
			PlayerEvaluator<A> evaluator1,
			PlayerEvaluator<A> evaluator2)
	{
		return new RatingEvaluatorComparator<A>(attributes)
				.compare(evaluator1, evaluator2);
	}

	@Override
	protected double getValue(
			A attributes,
			PlayerEvaluator<A> evaluator)
	{
		return evaluator.getRating(attributes);
	}
}
