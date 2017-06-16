package model;

public class Formation
{
	private String name;

	private Player pivot;
	private Player leftWing;
	private Player rightWing;
	private Player centerBack;
	private Player leftBack;
	private Player rightBack;

	public Formation()
	{
	}

	public Formation(String name)
	{
		this.name = name;
	}

	public Formation(
			String name,
			Player pivot,
			Player leftWing,
			Player rightWing,
			Player centerBack,
			Player leftBack,
			Player rightBack)
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

	public Player getPivot()
	{
		return pivot;
	}

	public void setPivot(Player pivot)
	{
		this.pivot = pivot;
	}

	public Player getLeftWing()
	{
		return leftWing;
	}

	public void setLeftWing(Player leftWing)
	{
		this.leftWing = leftWing;
	}

	public Player getRightWing()
	{
		return rightWing;
	}

	public void setRightWing(Player rightWing)
	{
		this.rightWing = rightWing;
	}

	public Player getCenterBack()
	{
		return centerBack;
	}

	public void setCenterBack(Player centerBack)
	{
		this.centerBack = centerBack;
	}

	public Player getLeftBack()
	{
		return leftBack;
	}

	public void setLeftBack(Player leftBack)
	{
		this.leftBack = leftBack;
	}

	public Player getRightBack()
	{
		return rightBack;
	}

	public void setRightBack(Player rightBack)
	{
		this.rightBack = rightBack;
	}

	@Override
	public String toString()
	{
		return String
				.format(
					"Pivot: %s\nLeft Wing: %s\nRight Wing: %s\nCenter Back: %s\nLeft Back: %s\nRight Back: %s\n",
					pivot.getName(),
					leftWing.getName(),
					rightWing.getName(),
					centerBack.getName(),
					leftBack.getName(),
					rightBack.getName());
	}
}
