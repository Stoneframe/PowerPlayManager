package model;

import model.handball.HandballAttributes;

public class HandballPlayer extends Player<HandballAttributes>
{
	public HandballPlayer(
			String name,
			Side side,
			HandballAttributes attributes)
	{
		super(name, side, attributes);
	}

	@Override
	public void mergeAttributes(Player<HandballAttributes> other)
	{
		this.attributes.merge(other.attributes);
	}
}
