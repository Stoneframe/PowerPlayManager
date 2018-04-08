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

	@Override
	public String toJson()
	{
		return "{" + 
				"name:\"" + name + 
				"\",\"age\":" + age + 
				",\"cl\":" + cl + 
				",\"side\":\"" + side + 
				"\",attributes:" + attributes.toJson() +
				",training:" + training +
				"}";
	}
}