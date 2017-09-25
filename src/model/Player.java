package model;

import util.AbstractModelObject;

public class Player<A extends Attributes>
	extends AbstractModelObject
{
	protected String name;
	protected int age;
	protected int cl;
	protected Side side;
	protected A attributes;
	protected double training;

	protected Player(String name, int age, int cl, Side side, A attributes, double training)
	{
		this.name = name;
		this.age = age;
		this.cl = cl;
		this.side = side;
		this.attributes = attributes;
		this.attributes.addPropertyChangedListener((s, e) -> firePropertyChanged(e));
		this.training = training;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
		firePropertyChanged("Name", name);
	}

	public int getAge()
	{
		return age;
	}

	public void setAge(int age)
	{
		this.age = age;
		firePropertyChanged("Age", age);
	}

	public int getCL()
	{
		return cl;
	}

	public void setCL(int cl)
	{
		this.cl = cl;
		firePropertyChanged("CL", cl);
	}

	public Side getSide()
	{
		return side;
	}

	public void setSide(Side side)
	{
		this.side = side;
		firePropertyChanged("Side", side);
	}

	public A getAttributes()
	{
		return attributes;
	}

	public double getTraining()
	{
		return training;
	}

	public void setTraining(double training)
	{
		this.training = training;
		firePropertyChanged("Traning", training);
	}

	public void merge(Player<A> other)
	{
		if (!this.equals(other)) return;

		if (this.age == 0)
		{
			this.setAge(other.getAge());
		}

		if (this.cl == 0)
		{
			this.setCL(other.getCL());
		}

		if (this.side == Side.UNKNOWN)
		{
			this.setSide(other.getSide());
		}

		this.attributes.merge(other.attributes);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == this)
			return true;

		if (!(obj instanceof Player<?>))
			return false;

		Player<?> other = (Player<?>) obj;

		return this.name.equals(other.name);
	}

	@Override
	public String toString()
	{
		return String.format("%s (%s)\n%s", name, side, attributes);
	}
}
