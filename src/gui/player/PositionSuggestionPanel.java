package gui.player;

import model.Attributes;
import model.PlayerEvaluator;
import model.comparators.RatingEvaluatorComparator;

public class PositionSuggestionPanel extends SuggestionPanel
{
	private static final long serialVersionUID = -1128283837238130849L;

	public PositionSuggestionPanel()
	{
		super("Position");
	}

	@Override
	protected int compare(
			Attributes attributes,
			PlayerEvaluator evaluator1,
			PlayerEvaluator evaluator2)
	{
		return new RatingEvaluatorComparator(attributes)
				.compare(evaluator1, evaluator2);
	}

	@Override
	protected double getValue(Attributes attributes, PlayerEvaluator evaluator)
	{
		return evaluator.getRating(attributes);
	}
}
