package model.football;

import model.Player;
import model.Side;

public class FootballPlayer
	extends Player<FootballAttributes>
{
	public FootballPlayer(
		String name,
		String country,
		int age,
		int cl,
		Side side,
		FootballAttributes attributes,
		int experience,
		int chemistry,
		int energy,
		double training)
	{
		super(name, country, age, cl, side, attributes, experience, chemistry, energy, training);
	}
}
