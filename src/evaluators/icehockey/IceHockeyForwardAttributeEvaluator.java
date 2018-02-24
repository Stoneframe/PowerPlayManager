package evaluators.icehockey;

import java.util.Arrays;

import evaluators.MacroAttributeEvaluator;
import model.icehockey.IceHockeyAttributes;

public class IceHockeyForwardAttributeEvaluator
	extends MacroAttributeEvaluator<IceHockeyAttributes>
{

	public IceHockeyForwardAttributeEvaluator()
	{
		super(
			"Forward",
			Arrays.asList(
				new IceHockeyCenterAttributeEvaluator(),
				new IceHockeyWingAttributeEvaluator()));
	}

}
