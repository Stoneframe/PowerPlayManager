package model.icehockey;

import model.Player;
import model.Side;

public class IceHockeyPlayer extends Player<IceHockeyAttributes>
{
	public IceHockeyPlayer(
			String name,
			int age,
			int cl,
			Side side,
			IceHockeyAttributes attributes,
			double training)
	{
		super(name, age, cl, side, attributes, training);
	}
}
