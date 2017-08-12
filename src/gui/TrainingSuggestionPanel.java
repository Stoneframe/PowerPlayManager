package gui;

import comparators.QualityEvaluatorComparator;
import evaluators.PlayerEvaluator;
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
			PlayerEvaluator<A> evaluator1,
			PlayerEvaluator<A> evaluator2)

	{
		return new QualityEvaluatorComparator<A>(attributes)
				.compare(evaluator1, evaluator2);
	}

	@Override
	protected double getValue(
			A attributes,
			PlayerEvaluator<A> evaluator)
	{
		return evaluator.getQuality(attributes);
	}
}
