package model.icehockey;

import model.Player;
import model.Side;

public class IceHockeyPlayer
	extends Player<IceHockeyAttributes>
{
	public IceHockeyPlayer(
		String name,
		String country,
		int age,
		int cl,
		Side side,
		IceHockeyAttributes attributes,
		int experience,
		int chemistry,
		int energy,
		double training)
	{
		super(name, country, age, cl, side, attributes, experience, chemistry, energy, training);
	}
}
