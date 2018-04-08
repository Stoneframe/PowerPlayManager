package model.football;

import model.Player;
import model.Side;

public class FootballPlayer
	extends Player<FootballAttributes>
{
	public FootballPlayer(
			String name,
			int age,
			int cl,
			Side side,
			FootballAttributes attributes,
			double training)
	{
		super(name, age, cl, side, attributes, training);
	}
	
	@Override
	public String toJson()
	{
		// TODO Auto-generated method stub
		return "not implemented";
	}
}
