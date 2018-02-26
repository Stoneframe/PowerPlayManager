package evaluators.icehockey;

import java.util.Arrays;

import evaluators.MacroAttributeEvaluator;
import model.icehockey.IceHockeyAttributes;

public class IceHockeyBackAttributeEvaluator
	extends MacroAttributeEvaluator<IceHockeyAttributes>
{
	public IceHockeyBackAttributeEvaluator()
	{
		super(
			"Back",
			Arrays.asList(
				new IceHockeyDefBackAttributeEvaluator(),
				new IceHockeyOffBackAttributeEvaluator()));
	}
}
