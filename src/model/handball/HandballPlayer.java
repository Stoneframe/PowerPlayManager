package model.handball;

import model.Player;
import model.Side;

public class HandballPlayer extends Player<HandballAttributes>
{
	public HandballPlayer(
			String name,
			int age,
			Side side,
			HandballAttributes attributes)
	{
		super(name, age, side, attributes);
	}
}
