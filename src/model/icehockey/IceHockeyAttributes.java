package model.icehockey;

import java.util.Arrays;
import java.util.stream.Collectors;

import model.Attribute;
import model.Attributes;

public class IceHockeyAttributes
	extends Attributes
{
	public IceHockeyAttributes()
	{
		super(
			Arrays.asList(
				new Attribute("Goa"),
				new Attribute("Def"),
				new Attribute("Off"),
				new Attribute("Sho"),
				new Attribute("Pas"),
				new Attribute("Tec"),
				new Attribute("Agr")));
	}

	public Attribute getGoa()
	{
		return attributes.get(0);
	}

	public Attribute getDef()
	{
		return attributes.get(1);
	}

	public Attribute getOff()
	{
		return attributes.get(2);
	}

	public Attribute getSho()
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

	public Attribute getAgr()
	{
		return attributes.get(6);
	}

	public IceHockeyAttributes copy()
	{
		IceHockeyAttributes other = new IceHockeyAttributes();

		other.attributes = this.attributes
			.stream()
			.map(a -> a.clone())
			.collect(Collectors.toList());

		return other;
	}
}
