package evaluators;

import model.Attribute;

public class Weight
{
	private String name;
	private double value;

	public Weight(String name, double value)
	{
		this.name = name;
		this.value = value;
	}

	public String getName()
	{
		return name;
	}

	public double getValue()
	{
		return value;
	}

	public double getRating(Attribute attribute)
	{
		return value != 0
				? attribute.getRating() / value
				: 0;
	}

	public double getQuality(Attribute attribute)
	{
		return value / attribute.getQuality();
	}
}
