package model;

import util.AbstractModelObject;

public class Player<A extends Attributes> extends AbstractModelObject
{
	protected String name;
	protected Side side;
	protected A attributes;

	protected Player(String name, Side side, A attributes)
	{
		this.name = name;
		this.side = side;
		this.attributes = attributes;
		this.attributes.addPropertyChangedListener(
			(s, e) -> firePropertyChanged(e));
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

	public void merge(Player<A> other)
	{
		if (!this.equals(other)) return;

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
