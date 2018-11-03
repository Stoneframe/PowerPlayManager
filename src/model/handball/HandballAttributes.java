package model.handball;

import java.util.Arrays;
import java.util.stream.Collectors;

import model.Attribute;
import model.Attributes;

public class HandballAttributes
	extends Attributes
{
	public HandballAttributes()
	{
		super(
			Arrays.asList(
				new Attribute("Goa"),
				new Attribute("Fip"),
				new Attribute("Sho"),
				new Attribute("Blk"),
				new Attribute("Pas"),
				new Attribute("Tec"),
				new Attribute("Spe"),
				new Attribute("Agr")));
	}

	public Attribute getGoa()
	{
		return attributes.get(0);
	}

	public Attribute getFip()
	{
		return attributes.get(1);
	}

	public Attribute getSho()
	{
		return attributes.get(2);
	}

	public Attribute getBlk()
	{
		return attributes.get(3);
	}

	public Attribute getPas()
	{
		return attributes.get(4);
	}

	public Attribute getTec()
	{
		return attributes.get(5);
	}

	public Attribute getSpe()
	{
		return attributes.get(6);
	}

	public Attribute getAgr()
	{
		return attributes.get(7);
	}

	public HandballAttributes copy()
	{
		HandballAttributes other = new HandballAttributes();

		other.attributes = this.attributes
			.stream()
			.map(a -> a.clone())
			.collect(Collectors.toList());

		return other;
	}
}
