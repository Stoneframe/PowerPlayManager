package formation.icehockey;

import java.util.Arrays;
import java.util.List;

import evaluators.AttributeEvaluator;
import evaluators.icehockey.IceHockeyBackAttributeEvaluator;
import evaluators.icehockey.IceHockeyCenterAttributeEvaluator;
import evaluators.icehockey.IceHockeyWingAttributeEvaluator;
import formation.FormationTemplate;
import formation.Position;
import model.Side;
import model.icehockey.IceHockeyAttributes;

public class IceHockeyFormationTemplate
	extends FormationTemplate<IceHockeyAttributes>
{
	public IceHockeyFormationTemplate(
			String name,
			List<Position<IceHockeyAttributes>> positions)
	{
		super(name, positions);
	}

	public static List<IceHockeyFormationTemplate> getStandardFormationTemplates()
	{
		return Arrays.asList(
			new IceHockeyFormationTemplate(
					"Normal",
					Arrays.asList(
						new Position<>(new IceHockeyWingAttributeEvaluator(), Side.LEFT),
						new Position<>(new IceHockeyCenterAttributeEvaluator(), Side.UNIVERSAL),
						new Position<>(new IceHockeyWingAttributeEvaluator(), Side.RIGHT),
						new Position<>(new IceHockeyBackAttributeEvaluator(), Side.LEFT),
						new Position<>(new IceHockeyBackAttributeEvaluator(), Side.RIGHT))));
	}

	public AttributeEvaluator<IceHockeyAttributes> getLeftWingEvaluator()
	{
		return positions.get(0).getAttributeEvaluator();
	}

	public AttributeEvaluator<IceHockeyAttributes> getCenterEvaluator()
	{
		return positions.get(1).getAttributeEvaluator();
	}

	public AttributeEvaluator<IceHockeyAttributes> getRightWingEvaluator()
	{
		return positions.get(2).getAttributeEvaluator();
	}

	public AttributeEvaluator<IceHockeyAttributes> getLeftBackEvaluator()
	{
		return positions.get(3).getAttributeEvaluator();
	}

	public AttributeEvaluator<IceHockeyAttributes> getRightBackEvaluator()
	{
		return positions.get(4).getAttributeEvaluator();
	}
}
