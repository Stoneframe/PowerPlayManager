package builders.formation.handball;

import builders.formation.FormationTemplate;
import evaluators.PlayerEvaluator;
import model.handball.HandballAttributes;

public class HandballFormationTemplate extends FormationTemplate
{
	private PlayerEvaluator<HandballAttributes> pivotEvaluator;
	private PlayerEvaluator<HandballAttributes> leftWingEvaluator;
	private PlayerEvaluator<HandballAttributes> rightWingEvaluator;
	private PlayerEvaluator<HandballAttributes> centerBackEvaluator;
	private PlayerEvaluator<HandballAttributes> leftBackEvaluator;
	private PlayerEvaluator<HandballAttributes> rightBackEvaluator;

	public HandballFormationTemplate(
			String name,
			PlayerEvaluator<HandballAttributes> pivotEvaluator,
			PlayerEvaluator<HandballAttributes> leftWingEvaluator,
			PlayerEvaluator<HandballAttributes> rightWingEvaluator,
			PlayerEvaluator<HandballAttributes> centerBackEvaluator,
			PlayerEvaluator<HandballAttributes> leftBackEvaluator,
			PlayerEvaluator<HandballAttributes> rightBackEvaluator)
	{
		super(name);
		this.pivotEvaluator = pivotEvaluator;
		this.leftWingEvaluator = leftWingEvaluator;
		this.rightWingEvaluator = rightWingEvaluator;
		this.centerBackEvaluator = centerBackEvaluator;
		this.leftBackEvaluator = leftBackEvaluator;
		this.rightBackEvaluator = rightBackEvaluator;
	}

	public PlayerEvaluator<HandballAttributes> getPivotEvaluator()
	{
		return pivotEvaluator;
	}

	public PlayerEvaluator<HandballAttributes> getLeftWingEvaluator()
	{
		return leftWingEvaluator;
	}

	public PlayerEvaluator<HandballAttributes> getRightWingEvaluator()
	{
		return rightWingEvaluator;
	}

	public PlayerEvaluator<HandballAttributes> getCenterBackEvaluator()
	{
		return centerBackEvaluator;
	}

	public PlayerEvaluator<HandballAttributes> getLeftBackEvaluator()
	{
		return leftBackEvaluator;
	}

	public PlayerEvaluator<HandballAttributes> getRightBackEvaluator()
	{
		return rightBackEvaluator;
	}
}
