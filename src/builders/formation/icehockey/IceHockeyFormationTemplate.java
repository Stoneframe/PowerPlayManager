package builders.formation.icehockey;

import builders.formation.FormationTemplate;
import evaluators.PlayerEvaluator;
import model.icehockey.IceHockeyAttributes;

public class IceHockeyFormationTemplate extends FormationTemplate
{
	private PlayerEvaluator<IceHockeyAttributes> leftWingEvaluator;
	private PlayerEvaluator<IceHockeyAttributes> centerEvaluator;
	private PlayerEvaluator<IceHockeyAttributes> rightWingEvaluator;
	private PlayerEvaluator<IceHockeyAttributes> leftBackEvaluator;
	private PlayerEvaluator<IceHockeyAttributes> rightBackEvaluator;

	public IceHockeyFormationTemplate(
			String name,
			PlayerEvaluator<IceHockeyAttributes> leftWingEvaluator,
			PlayerEvaluator<IceHockeyAttributes> centerBackEvaluator,
			PlayerEvaluator<IceHockeyAttributes> rightWingEvaluator,
			PlayerEvaluator<IceHockeyAttributes> leftBackEvaluator,
			PlayerEvaluator<IceHockeyAttributes> rightBackEvaluator)
	{
		super(name);
		this.leftWingEvaluator = leftWingEvaluator;
		this.centerEvaluator = centerBackEvaluator;
		this.rightWingEvaluator = rightWingEvaluator;
		this.leftBackEvaluator = leftBackEvaluator;
		this.rightBackEvaluator = rightBackEvaluator;
	}

	public PlayerEvaluator<IceHockeyAttributes> getLeftWingEvaluator()
	{
		return leftWingEvaluator;
	}

	public void setLeftWingEvaluator(PlayerEvaluator<IceHockeyAttributes> leftWingEvaluator)
	{
		this.leftWingEvaluator = leftWingEvaluator;
	}

	public PlayerEvaluator<IceHockeyAttributes> getCenterEvaluator()
	{
		return centerEvaluator;
	}

	public void setCenterEvaluator(PlayerEvaluator<IceHockeyAttributes> centerEvaluator)
	{
		this.centerEvaluator = centerEvaluator;
	}

	public PlayerEvaluator<IceHockeyAttributes> getRightWingEvaluator()
	{
		return rightWingEvaluator;
	}

	public void setRightWingEvaluator(PlayerEvaluator<IceHockeyAttributes> rightWingEvaluator)
	{
		this.rightWingEvaluator = rightWingEvaluator;
	}

	public PlayerEvaluator<IceHockeyAttributes> getLeftBackEvaluator()
	{
		return leftBackEvaluator;
	}

	public void setLeftBackEvaluator(PlayerEvaluator<IceHockeyAttributes> leftBackEvaluator)
	{
		this.leftBackEvaluator = leftBackEvaluator;
	}

	public PlayerEvaluator<IceHockeyAttributes> getRightBackEvaluator()
	{
		return rightBackEvaluator;
	}

	public void setRightBackEvaluator(PlayerEvaluator<IceHockeyAttributes> rightBackEvaluator)
	{
		this.rightBackEvaluator = rightBackEvaluator;
	}
}
