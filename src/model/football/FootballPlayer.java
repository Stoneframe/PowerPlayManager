package model.football;

import model.Player;
import model.Side;

public class FootballPlayer
	extends Player<FootballAttributes>
{

	protected FootballPlayer(
			String name,
			int age,
			int cl,
			Side side,
			FootballAttributes attributes,
			double training)
	{
		super(name, age, cl, side, attributes, training);
	}
}
