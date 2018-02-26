package formation.handball;

import java.util.Arrays;
import java.util.List;

import evaluators.AttributeEvaluator;
import evaluators.handball.HandballDefBackAttributeEvaluator;
import evaluators.handball.HandballDefPivotAttributeEvaluator;
import evaluators.handball.HandballDefWingAttributeEvaluator;
import evaluators.handball.HandballOffBackAttributeEvaluator;
import evaluators.handball.HandballOffPivotAttributeEvaluator;
import evaluators.handball.HandballOffWingAttributeEvaluator;
import formation.FormationTemplate;
import formation.Position;
import model.Side;
import model.handball.HandballAttributes;

public class HandballFormationTemplate
	extends FormationTemplate<HandballAttributes>
{
	public HandballFormationTemplate(
			String name,
			List<Position<HandballAttributes>> positions)
	{
		super(name, positions);
	}

	public static List<HandballFormationTemplate> getStandardFormationTemplates()
	{
		return Arrays.asList(
			new HandballFormationTemplate(
					"Offensive",
					Arrays.asList(
						new Position<>(new HandballOffPivotAttributeEvaluator(), Side.UNIVERSAL),
						new Position<>(new HandballOffWingAttributeEvaluator(), Side.LEFT),
						new Position<>(new HandballOffWingAttributeEvaluator(), Side.RIGHT),
						new Position<>(new HandballOffBackAttributeEvaluator(), Side.LEFT),
						new Position<>(new HandballOffBackAttributeEvaluator(), Side.UNIVERSAL),
						new Position<>(new HandballOffBackAttributeEvaluator(), Side.RIGHT))),
			new HandballFormationTemplate(
					"Defensive",
					Arrays.asList(
						new Position<>(new HandballDefPivotAttributeEvaluator(), Side.UNIVERSAL),
						new Position<>(new HandballDefWingAttributeEvaluator(), Side.LEFT),
						new Position<>(new HandballDefWingAttributeEvaluator(), Side.RIGHT),
						new Position<>(new HandballDefBackAttributeEvaluator(), Side.LEFT),
						new Position<>(new HandballDefBackAttributeEvaluator(), Side.UNIVERSAL),
						new Position<>(new HandballDefBackAttributeEvaluator(), Side.RIGHT))));
	}

	public AttributeEvaluator<HandballAttributes> getPivotEvaluator()
	{
		return positions.get(0).getAttributeEvaluator();
	}

	public AttributeEvaluator<HandballAttributes> getLeftWingEvaluator()
	{
		return positions.get(1).getAttributeEvaluator();
	}

	public AttributeEvaluator<HandballAttributes> getRightWingEvaluator()
	{
		return positions.get(2).getAttributeEvaluator();
	}

	public AttributeEvaluator<HandballAttributes> getCenterBackEvaluator()
	{
		return positions.get(3).getAttributeEvaluator();
	}

	public AttributeEvaluator<HandballAttributes> getLeftBackEvaluator()
	{
		return positions.get(4).getAttributeEvaluator();
	}

	public AttributeEvaluator<HandballAttributes> getRightBackEvaluator()
	{
		return positions.get(5).getAttributeEvaluator();
	}
}
