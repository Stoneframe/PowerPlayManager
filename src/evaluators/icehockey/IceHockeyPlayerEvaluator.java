package evaluators.icehockey;

import java.util.List;

import calendar.icehockey.IceHockeyCalendar;
import evaluators.AttributeEvaluator;
import evaluators.PlayerEvaluator;
import model.icehockey.IceHockeyAttributes;
import settings.SportSettings;

public class IceHockeyPlayerEvaluator
	extends PlayerEvaluator<IceHockeyAttributes>
{
	public IceHockeyPlayerEvaluator(
		SportSettings settings,
		List<AttributeEvaluator<IceHockeyAttributes>> attributeEvaluators)
	{
		super(settings, new IceHockeyCalendar(), attributeEvaluators);
	}
}
