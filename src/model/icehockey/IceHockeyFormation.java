package model.icehockey;

import model.Formation;
import model.Player;

public class IceHockeyFormation extends Formation
{
	private Player<IceHockeyAttributes> leftWing;
	private Player<IceHockeyAttributes> center;
	private Player<IceHockeyAttributes> rightWing;
	private Player<IceHockeyAttributes> leftBack;
	private Player<IceHockeyAttributes> rightBack;

	public IceHockeyFormation(String name)
	{
		super(name);
	}

	public IceHockeyFormation(
			String name,
			Player<IceHockeyAttributes> leftWing,
			Player<IceHockeyAttributes> center,
			Player<IceHockeyAttributes> rightWing,
			Player<IceHockeyAttributes> leftBack,
			Player<IceHockeyAttributes> rightBack)
	{
		super(name);

		this.leftWing = leftWing;
		this.center = center;
		this.rightWing = rightWing;
		this.leftBack = leftBack;
		this.rightBack = rightBack;
	}

	public Player<IceHockeyAttributes> getLeftWing()
	{
		return leftWing;
	}

	public void setLeftWing(Player<IceHockeyAttributes> leftWing)
	{
		this.leftWing = leftWing;
	}

	public Player<IceHockeyAttributes> getCenter()
	{
		return center;
	}

	public void setCenter(Player<IceHockeyAttributes> center)
	{
		this.center = center;
	}

	public Player<IceHockeyAttributes> getRightWing()
	{
		return rightWing;
	}

	public void setRightWing(Player<IceHockeyAttributes> rightWing)
	{
		this.rightWing = rightWing;
	}

	public Player<IceHockeyAttributes> getLeftBack()
	{
		return leftBack;
	}

	public void setLeftBack(Player<IceHockeyAttributes> leftBack)
	{
		this.leftBack = leftBack;
	}

	public Player<IceHockeyAttributes> getRightBack()
	{
		return rightBack;
	}

	public void setRightBack(Player<IceHockeyAttributes> rightBack)
	{
		this.rightBack = rightBack;
	}
}
