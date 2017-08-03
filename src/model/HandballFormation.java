package model;

public class HandballFormation
{
	private String name;

	private HandballPlayer pivot;
	private HandballPlayer leftWing;
	private HandballPlayer rightWing;
	private HandballPlayer centerBack;
	private HandballPlayer leftBack;
	private HandballPlayer rightBack;

	public HandballFormation()
	{
	}

	public HandballFormation(String name)
	{
		this.name = name;
	}

	public HandballFormation(
			String name,
			HandballPlayer pivot,
			HandballPlayer leftWing,
			HandballPlayer rightWing,
			HandballPlayer centerBack,
			HandballPlayer leftBack,
			HandballPlayer rightBack)
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

	public HandballPlayer getPivot()
	{
		return pivot;
	}

	public void setPivot(HandballPlayer pivot)
	{
		this.pivot = pivot;
	}

	public HandballPlayer getLeftWing()
	{
		return leftWing;
	}

	public void setLeftWing(HandballPlayer leftWing)
	{
		this.leftWing = leftWing;
	}

	public HandballPlayer getRightWing()
	{
		return rightWing;
	}

	public void setRightWing(HandballPlayer rightWing)
	{
		this.rightWing = rightWing;
	}

	public HandballPlayer getCenterBack()
	{
		return centerBack;
	}

	public void setCenterBack(HandballPlayer centerBack)
	{
		this.centerBack = centerBack;
	}

	public HandballPlayer getLeftBack()
	{
		return leftBack;
	}

	public void setLeftBack(HandballPlayer leftBack)
	{
		this.leftBack = leftBack;
	}

	public HandballPlayer getRightBack()
	{
		return rightBack;
	}

	public void setRightBack(HandballPlayer rightBack)
	{
		this.rightBack = rightBack;
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
