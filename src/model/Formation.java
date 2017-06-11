package model;

public class Formation
{
	private Player pivot;
	private Player leftWing;
	private Player rightWing;
	private Player centerBack;
	private Player leftBack;
	private Player rightBack;

	public Formation(
			Player pivot,
			Player leftWing,
			Player rightWing,
			Player centerBack,
			Player leftBack,
			Player rightBack)
	{
		this.pivot = pivot;
		this.leftWing = leftWing;
		this.rightWing = rightWing;
		this.centerBack = centerBack;
		this.leftBack = leftBack;
		this.rightBack = rightBack;
	}

	public Player getPivot()
	{
		return pivot;
	}

	public Player getLeftWing()
	{
		return leftWing;
	}

	public Player getRightWing()
	{
		return rightWing;
	}

	public Player getCenterBack()
	{
		return centerBack;
	}

	public Player getLeftBack()
	{
		return leftBack;
	}

	public Player getRightBack()
	{
		return rightBack;
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
