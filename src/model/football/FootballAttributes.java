package model.football;

import java.util.Arrays;
import java.util.stream.Collectors;

import model.Attribute;
import model.Attributes;

public class FootballAttributes
	extends Attributes
{
	public FootballAttributes()
	{
		super(
			Arrays.asList(
				new Attribute("Goa"),
				new Attribute("Def"),
				new Attribute("Mid"),
				new Attribute("Off"),
				new Attribute("Sho"),
				new Attribute("Pas"),
				new Attribute("Tec"),
				new Attribute("Spe"),
				new Attribute("Hea")));
	}

	public Attribute getGoa()
	{
		return attributes.get(0);
	}

	public Attribute getDef()
	{
		return attributes.get(1);
	}

	public Attribute getMid()
	{
		return attributes.get(2);
	}

	public Attribute getOff()
	{
		return attributes.get(3);
	}

	public Attribute getSho()
	{
		return attributes.get(4);
	}

	public Attribute getPas()
	{
		return attributes.get(5);
	}

	public Attribute getTec()
	{
		return attributes.get(6);
	}

	public Attribute getSpe()
	{
		return attributes.get(7);
	}

	public Attribute getHea()
	{
		return attributes.get(8);
	}

	public FootballAttributes copy()
	{
		FootballAttributes other = new FootballAttributes();

		other.attributes = this.attributes
			.stream()
			.map(a -> a.clone())
			.collect(Collectors.toList());

		return other;
	}
}
