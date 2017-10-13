package formation.icehockey;

import evaluators.AttributeEvaluator;
import formation.FormationTemplate;
import model.icehockey.IceHockeyAttributes;

public class IceHockeyFormationTemplate
	extends FormationTemplate
{
	private AttributeEvaluator<IceHockeyAttributes> leftWingEvaluator;
	private AttributeEvaluator<IceHockeyAttributes> centerEvaluator;
	private AttributeEvaluator<IceHockeyAttributes> rightWingEvaluator;
	private AttributeEvaluator<IceHockeyAttributes> leftBackEvaluator;
	private AttributeEvaluator<IceHockeyAttributes> rightBackEvaluator;

	public IceHockeyFormationTemplate(
			String name,
			AttributeEvaluator<IceHockeyAttributes> leftWingEvaluator,
			AttributeEvaluator<IceHockeyAttributes> centerBackEvaluator,
			AttributeEvaluator<IceHockeyAttributes> rightWingEvaluator,
			AttributeEvaluator<IceHockeyAttributes> leftBackEvaluator,
			AttributeEvaluator<IceHockeyAttributes> rightBackEvaluator)
	{
		super(name, 5);
		this.leftWingEvaluator = leftWingEvaluator;
		this.centerEvaluator = centerBackEvaluator;
		this.rightWingEvaluator = rightWingEvaluator;
		this.leftBackEvaluator = leftBackEvaluator;
		this.rightBackEvaluator = rightBackEvaluator;
	}

	public AttributeEvaluator<IceHockeyAttributes> getLeftWingEvaluator()
	{
		return leftWingEvaluator;
	}

	public void setLeftWingEvaluator(AttributeEvaluator<IceHockeyAttributes> leftWingEvaluator)
	{
		this.leftWingEvaluator = leftWingEvaluator;
	}

	public AttributeEvaluator<IceHockeyAttributes> getCenterEvaluator()
	{
		return centerEvaluator;
	}

	public void setCenterEvaluator(AttributeEvaluator<IceHockeyAttributes> centerEvaluator)
	{
		this.centerEvaluator = centerEvaluator;
	}

	public AttributeEvaluator<IceHockeyAttributes> getRightWingEvaluator()
	{
		return rightWingEvaluator;
	}

	public void setRightWingEvaluator(AttributeEvaluator<IceHockeyAttributes> rightWingEvaluator)
	{
		this.rightWingEvaluator = rightWingEvaluator;
	}

	public AttributeEvaluator<IceHockeyAttributes> getLeftBackEvaluator()
	{
		return leftBackEvaluator;
	}

	public void setLeftBackEvaluator(AttributeEvaluator<IceHockeyAttributes> leftBackEvaluator)
	{
		this.leftBackEvaluator = leftBackEvaluator;
	}

	public AttributeEvaluator<IceHockeyAttributes> getRightBackEvaluator()
	{
		return rightBackEvaluator;
	}

	public void setRightBackEvaluator(AttributeEvaluator<IceHockeyAttributes> rightBackEvaluator)
	{
		this.rightBackEvaluator = rightBackEvaluator;
	}
}
