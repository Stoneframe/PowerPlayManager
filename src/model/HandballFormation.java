package model;

import model.handball.HandballAttributes;

public class HandballFormation
{
	private String name;

	private Player<HandballAttributes> pivot;
	private Player<HandballAttributes> leftWing;
	private Player<HandballAttributes> rightWing;
	private Player<HandballAttributes> centerBack;
	private Player<HandballAttributes> leftBack;
	private Player<HandballAttributes> rightBack;

	public HandballFormation()
	{
	}

	public HandballFormation(String name)
	{
		this.name = name;
	}

	public HandballFormation(
			String name,
			Player<HandballAttributes> pivot,
			Player<HandballAttributes> leftWing,
			Player<HandballAttributes> rightWing,
			Player<HandballAttributes> centerBack,
			Player<HandballAttributes> leftBack,
			Player<HandballAttributes> rightBack)
	{
		this.name = name;
		this.pivot = pivot;
		this.leftWing = leftWing;
		this.rightWing = rightWing;
		this.centerBack = centerBack;
		this.leftBack = leftBack;
		this.rightBack = rightBack;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Player<HandballAttributes> getPivot()
	{
		return pivot;
	}

	public void setPivot(Player<HandballAttributes> pivot)
	{
		this.pivot = pivot;
	}

	public Player<HandballAttributes> getLeftWing()
	{
		return leftWing;
	}

	public void setLeftWing(Player<HandballAttributes> leftWing)
	{
		this.leftWing = leftWing;
	}

	public Player<HandballAttributes> getRightWing()
	{
		return rightWing;
	}

	public void setRightWing(Player<HandballAttributes> rightWing)
	{
		this.rightWing = rightWing;
	}

	public Player<HandballAttributes> getCenterBack()
	{
		return centerBack;
	}

	public void setCenterBack(Player<HandballAttributes> centerBack)
	{
		this.centerBack = centerBack;
	}

	public Player<HandballAttributes> getLeftBack()
	{
		return leftBack;
	}

	public void setLeftBack(Player<HandballAttributes> leftBack)
	{
		this.leftBack = leftBack;
	}

	public Player<HandballAttributes> getRightBack()
	{
		return rightBack;
	}

	public void setRightBack(Player<HandballAttributes> player)
	{
		this.rightBack = player;
	}

	@Override
	public String toString()
	{
		return String.format(
			"Pivot: %s\nLeft Wing: %s\nRight Wing: %s\nCenter Back: %s\nLeft Back: %s\nRight Back: %s\n",
			pivot.getName(),
			leftWing.getName(),
			rightWing.getName(),
			centerBack.getName(),
			leftBack.getName(),
			rightBack.getName());
	}
}
