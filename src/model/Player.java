package model;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Player extends AbstractModelObject
{
	private String name;
	private Side side;
	private Attributes attributes;

	public Player(String name, Side side, Attributes attributes)
	{
		this.name = name;
		this.side = side;
		this.attributes = attributes;
		this.attributes
				.addPropertyChangedListener((s, e) -> firePropertyChanged(e));
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

	public Attributes getAttributes()
	{
		return attributes;
	}

	public void merge(Player other)
	{
		if (!this.equals(other))
			return;

		if (this.side == Side.UNKNOWN)
			this.setSide(other.getSide());

		mergeAttribute(
			() -> this.attributes.getGoa(),
			(v) -> this.attributes.setGoa(v),
			() -> other.attributes.getGoa());

		mergeAttribute(
			() -> this.attributes.getFip(),
			(v) -> this.attributes.setFip(v),
			() -> other.attributes.getFip());

		mergeAttribute(
			() -> this.attributes.getSho(),
			(v) -> this.attributes.setSho(v),
			() -> other.attributes.getSho());

		mergeAttribute(
			() -> this.attributes.getBlk(),
			(v) -> this.attributes.setBlk(v),
			() -> other.attributes.getBlk());

		mergeAttribute(
			() -> this.attributes.getPas(),
			(v) -> this.attributes.setPas(v),
			() -> other.attributes.getPas());

		mergeAttribute(
			() -> this.attributes.getTec(),
			(v) -> this.attributes.setTec(v),
			() -> other.attributes.getTec());

		mergeAttribute(
			() -> this.attributes.getSpe(),
			(v) -> this.attributes.setSpe(v),
			() -> other.attributes.getSpe());

		mergeAttribute(
			() -> this.attributes.getAgr(),
			(v) -> this.attributes.setAgr(v),
			() -> other.attributes.getAgr());

		mergeAttribute(
			() -> this.attributes.getQGoa(),
			(v) -> this.attributes.setQGoa(v),
			() -> other.attributes.getQGoa());

		mergeAttribute(
			() -> this.attributes.getQFip(),
			(v) -> this.attributes.setQFip(v),
			() -> other.attributes.getQFip());

		mergeAttribute(
			() -> this.attributes.getQSho(),
			(v) -> this.attributes.setQSho(v),
			() -> other.attributes.getQSho());

		mergeAttribute(
			() -> this.attributes.getQBlk(),
			(v) -> this.attributes.setQBlk(v),
			() -> other.attributes.getQBlk());

		mergeAttribute(
			() -> this.attributes.getQPas(),
			(v) -> this.attributes.setQPas(v),
			() -> other.attributes.getQPas());

		mergeAttribute(
			() -> this.attributes.getQTec(),
			(v) -> this.attributes.setQTec(v),
			() -> other.attributes.getQTec());

		mergeAttribute(
			() -> this.attributes.getQSpe(),
			(v) -> this.attributes.setQSpe(v),
			() -> other.attributes.getQSpe());

		mergeAttribute(
			() -> this.attributes.getQAgr(),
			(v) -> this.attributes.setQAgr(v),
			() -> other.attributes.getQAgr());
	}

	private void mergeAttribute(
			Supplier<Integer> thisGet,
			Consumer<Integer> thisSet,
			Supplier<Integer> otherGet)
	{
		if (thisGet.get() == 0)
		{
			thisSet.accept(otherGet.get());
		}
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == this)
			return true;

		if (!(obj instanceof Player))
			return false;

		Player other = (Player) obj;

		return this.name.equals(other.name);
	}

	@Override
	public String toString()
	{
		return String.format("%s (%s)\n%s", name, side, attributes);
	}
}
