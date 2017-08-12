package model.icehockey;

import model.Player;
import model.Side;

public class IceHockeyPlayer extends Player<IceHockeyAttributes>
{
	public IceHockeyPlayer(
			String name,
			Side side,
			IceHockeyAttributes attributes)
	{
		super(name, side, attributes);
	}
}
