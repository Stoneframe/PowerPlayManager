package model.handball;

import model.Player;
import model.Side;

public class HandballPlayer extends Player<HandballAttributes>
{
	public HandballPlayer(
			String name,
			int age,
			int cl,
			Side side,
			HandballAttributes attributes)
	{
		super(name, age, cl, side, attributes);
	}
}
