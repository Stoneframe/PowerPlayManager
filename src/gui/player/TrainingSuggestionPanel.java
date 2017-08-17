package gui.player;

import comparators.QualityEvaluatorComparator;
import evaluators.AttributeEvaluator;
import model.Attributes;

public class TrainingSuggestionPanel<A extends Attributes>
		extends SuggestionPanel<A>
{
	private static final long serialVersionUID = -9177249749398783715L;

	public TrainingSuggestionPanel()
	{
		super("Traning");
	}

	@Override
	protected int compare(
			A attributes,
			AttributeEvaluator<A> evaluator1,
			AttributeEvaluator<A> evaluator2)

	{
		return new QualityEvaluatorComparator<A>(attributes)
				.compare(evaluator1, evaluator2);
	}

	@Override
	protected double getValue(
			A attributes,
			AttributeEvaluator<A> evaluator)
	{
		return evaluator.getQuality(attributes);
	}
}
