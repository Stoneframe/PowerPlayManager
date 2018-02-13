package evaluators.icehockey;

import java.util.Arrays;

import evaluators.MacroAttributeEvaluator;
import model.icehockey.IceHockeyAttributes;

public class IceHockeyOffensiveAttributeEvaluator
	extends MacroAttributeEvaluator<IceHockeyAttributes>
{
	public IceHockeyOffensiveAttributeEvaluator()
	{
		super(
				"Offensive",
				Arrays.asList(
					new IceHockeyCenterAttributeEvaluator(),
					new IceHockeyWingAttributeEvaluator(),
					new IceHockeyOffBackAttributeEvaluator()));
	}
}
