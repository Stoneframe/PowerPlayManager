package evaluators.icehockey;

import java.util.Arrays;
import java.util.List;

import comparators.QualityEvaluatorComparator;
import comparators.RatingEvaluatorComparator;
import model.icehockey.IceHockeyAttributes;

public class IceHockeyBackAttributeEvaluator
	extends IceHockeyAttributeEvaluator
{
	private List<IceHockeyAttributeEvaluator> attributeEvaluators =
			Arrays.asList(
				new IceHockeyDefBackAttributeEvaluator(),
				new IceHockeyOffBackAttributeEvaluator());

	public IceHockeyBackAttributeEvaluator()
	{
		super("Back", 0, 0, 0, 0, 0, 0, 0);
	}
	
	@Override
	public boolean isMacroPosition()
	{
		return true;
	}

	@Override
	public double getRating(IceHockeyAttributes attributes)
	{
		return attributeEvaluators
				.stream()
				.max(new RatingEvaluatorComparator<IceHockeyAttributes>(attributes)::compare)
				.get()
				.getRating(attributes);
	}

	@Override
	public double getQuality(IceHockeyAttributes attributes)
	{
		return attributeEvaluators
				.stream()
				.max(new QualityEvaluatorComparator<IceHockeyAttributes>(attributes)::compare)
				.get()
				.getQuality(attributes);
	}
}
