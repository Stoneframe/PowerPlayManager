package model.handball;

import com.google.gson.Gson;

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
			double training)
	{
		super(name, age, cl, side, attributes, training);
	}

	@Override
	public String toJson()
	{
		Gson gson = new Gson();
//		Type typeOfSrc = new TypeToken<Collection<Foo>>(){}.getType();
//		String att = gson.toJson(attributes);
//		System.out.println("Json attributes: " + att);
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
