package gui;

import model.Attributes;
import model.PlayerEvaluator;
import model.comparators.QualityEvaluatorComparator;

public class TrainingSuggestionPanel extends SuggestionPanel
{
	private static final long serialVersionUID = -9177249749398783715L;

	public TrainingSuggestionPanel()
	{
		super("Traning");
	}

	@Override
	protected int compare(
			Attributes attributes,
			PlayerEvaluator evaluator1,
			PlayerEvaluator evaluator2)

	{
		return new QualityEvaluatorComparator(attributes)
				.compare(evaluator1, evaluator2);
	}

	@Override
	protected double getValue(Attributes attributes, PlayerEvaluator evaluator)
	{
		return evaluator.getQuality(attributes);
	}
}
