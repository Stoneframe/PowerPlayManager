package model.icehockey;

import model.Player;
import model.Side;

public class IceHockeyPlayer
	extends Player<IceHockeyAttributes>
{
	public IceHockeyPlayer(
			String name,
			int age,
			int cl,
			Side side,
			IceHockeyAttributes attributes,
			int experience,
			int chemistry,
			int energy,
			double training)
	{
		super(name, age, cl, side, attributes, experience, chemistry, energy, training);
	}

	@Override
	public String toJson()
	{
		// TODO Auto-generated method stub
		return "not implemented";
	}
}
