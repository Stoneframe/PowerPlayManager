package model.handball;

import model.Player;
import model.Side;

public class HandballPlayer
	extends Player<HandballAttributes>
{
	public HandballPlayer(
		String name,
		int age,
		int cl,
		Side side,
		HandballAttributes attributes,
		int experience,
		int chemistry,
		int energy,
		double training)
	{
		super(name, age, cl, side, attributes, experience, chemistry, energy, training);
	}
}