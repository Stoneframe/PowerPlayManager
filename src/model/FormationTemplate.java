package model;

public class FormationTemplate
{
	private String name;

	private PlayerEvaluator pivotEvaluator;
	private PlayerEvaluator leftWingEvaluator;
	private PlayerEvaluator rightWingEvaluator;
	private PlayerEvaluator centerBackEvaluator;
	private PlayerEvaluator leftBackEvaluator;
	private PlayerEvaluator rightBackEvaluator;

	public FormationTemplate(
			String name,
			PlayerEvaluator pivotEvaluator,
			PlayerEvaluator leftWingEvaluator,
			PlayerEvaluator rightWingEvaluator,
			PlayerEvaluator centerBackEvaluator,
			PlayerEvaluator leftBackEvaluator,
			PlayerEvaluator rightBackEvaluator)
	{
		this.name = name;
		this.pivotEvaluator = pivotEvaluator;
		this.leftWingEvaluator = leftWingEvaluator;
		this.rightWingEvaluator = rightWingEvaluator;
		this.centerBackEvaluator = centerBackEvaluator;
		this.leftBackEvaluator = leftBackEvaluator;
		this.rightBackEvaluator = rightBackEvaluator;
	}

	public String getName()
	{
		return name;
	}

	public PlayerEvaluator getPivotEvaluator()
	{
		return pivotEvaluator;
	}

	public PlayerEvaluator getLeftWingEvaluator()
	{
		return leftWingEvaluator;
	}

	public PlayerEvaluator getRightWingEvaluator()
	{
		return rightWingEvaluator;
	}

	public PlayerEvaluator getCenterBackEvaluator()
	{
		return centerBackEvaluator;
	}

	public PlayerEvaluator getLeftBackEvaluator()
	{
		return leftBackEvaluator;
	}

	public PlayerEvaluator getRightBackEvaluator()
	{
		return rightBackEvaluator;
	}

	@Override
	public String toString()
	{
		return getName();
	}
}
