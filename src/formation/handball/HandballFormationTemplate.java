package formation.handball;

import evaluators.AttributeEvaluator;
import formation.FormationTemplate;
import model.handball.HandballAttributes;

public class HandballFormationTemplate extends FormationTemplate
{
	private AttributeEvaluator<HandballAttributes> pivotEvaluator;
	private AttributeEvaluator<HandballAttributes> leftWingEvaluator;
	private AttributeEvaluator<HandballAttributes> rightWingEvaluator;
	private AttributeEvaluator<HandballAttributes> centerBackEvaluator;
	private AttributeEvaluator<HandballAttributes> leftBackEvaluator;
	private AttributeEvaluator<HandballAttributes> rightBackEvaluator;

	public HandballFormationTemplate(
			String name,
			AttributeEvaluator<HandballAttributes> pivotEvaluator,
			AttributeEvaluator<HandballAttributes> leftWingEvaluator,
			AttributeEvaluator<HandballAttributes> rightWingEvaluator,
			AttributeEvaluator<HandballAttributes> centerBackEvaluator,
			AttributeEvaluator<HandballAttributes> leftBackEvaluator,
			AttributeEvaluator<HandballAttributes> rightBackEvaluator)
	{
		super(name);
		this.pivotEvaluator = pivotEvaluator;
		this.leftWingEvaluator = leftWingEvaluator;
		this.rightWingEvaluator = rightWingEvaluator;
		this.centerBackEvaluator = centerBackEvaluator;
		this.leftBackEvaluator = leftBackEvaluator;
		this.rightBackEvaluator = rightBackEvaluator;
	}

	public AttributeEvaluator<HandballAttributes> getPivotEvaluator()
	{
		return pivotEvaluator;
	}

	public AttributeEvaluator<HandballAttributes> getLeftWingEvaluator()
	{
		return leftWingEvaluator;
	}

	public AttributeEvaluator<HandballAttributes> getRightWingEvaluator()
	{
		return rightWingEvaluator;
	}

	public AttributeEvaluator<HandballAttributes> getCenterBackEvaluator()
	{
		return centerBackEvaluator;
	}

	public AttributeEvaluator<HandballAttributes> getLeftBackEvaluator()
	{
		return leftBackEvaluator;
	}

	public AttributeEvaluator<HandballAttributes> getRightBackEvaluator()
	{
		return rightBackEvaluator;
	}
}
