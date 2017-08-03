package gui;

import comparators.QualityEvaluatorComparator;
import evaluators.PlayerEvaluator;
import model.Attributes;

public class TrainingSuggestionPanel
		extends
		SuggestionPanel
{
	private static final long serialVersionUID = -9177249749398783715L;

	public TrainingSuggestionPanel()
	{
		super("Traning");
	}

	@Override
	protected int compare(
			A attributes,
			PlayerEvaluator<Attributes> evaluator1,
			PlayerEvaluator<Attributes> evaluator2)

	{
		return new QualityEvaluatorComparator(attributes)
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
