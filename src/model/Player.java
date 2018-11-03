package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
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

	private final IntegerProperty experience = new SimpleIntegerProperty();
	private final IntegerProperty chemistry = new SimpleIntegerProperty();
	private final IntegerProperty energy = new SimpleIntegerProperty();

	public Player(
			String name,
			int age,
			int cl,
			Side side,
			A attributes,
			int experience,
			int chemistry,
			int energy,
			double training)
	{
		this.name = name;
		this.age = age;
		this.cl = cl;
		this.side = side;
		this.attributes = attributes;
		this.attributes.addPropertyChangedListener((s, e) -> firePropertyChanged(e));
		setExperience(experience);
		setChemistry(chemistry);
		setEnergy(energy);
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

	public int getExperience()
	{
		return experienceProperty().get();
	}

	public void setExperience(int experience)
	{
		experienceProperty().set(experience);
		firePropertyChanged("Experience", experience);
	}

	public IntegerProperty experienceProperty()
	{
		return experience;
	}

	public int getChemistry()
	{
		return chemistryProperty().get();
	}

	public void setChemistry(int chemistry)
	{
		chemistryProperty().set(chemistry);
		firePropertyChanged("Chemistry", chemistry);
	}

	public IntegerProperty chemistryProperty()
	{
		return chemistry;
	}

	public int getEnergy()
	{
		return energyProperty().get();
	}

	public void setEnergy(int energy)
	{
		energyProperty().set(energy);
		firePropertyChanged("Energy", energy);
	}

	public IntegerProperty energyProperty()
	{
		return energy;
	}

	public double getTraining()
	{
		return training;
	}

	public void setTraining(double training)
	{
		this.training = training;
		firePropertyChanged("Training", training);
	}

	public void merge(Player<A> other)
	{
		if (!this.equals(other)) return;

		if (other.getAge() > this.getAge())
		{
			this.setAge(other.getAge());
		}

		if (this.getCL() == -1)
		{
			this.setCL(other.getCL());
		}

		if (this.side == Side.UNKNOWN)
		{
			this.setSide(other.getSide());
		}

		if (other.getExperience() > this.getExperience())
		{
			this.setExperience(other.getExperience());
		}

		if (other.getChemistry() != 0)
		{
			this.setChemistry(other.getChemistry());
		}

		if (other.getEnergy() < this.getEnergy())
		{
			this.setEnergy(other.getEnergy());
		}

		this.attributes.merge(other.attributes);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == this) return true;

		if (!(obj instanceof Player<?>)) return false;

		Player<?> other = (Player<?>)obj;

		return this.name.equals(other.name);
	}

	@Override
	public String toString()
	{
		return String.format("%s (%s)\n%s", name, side, attributes);
	}
}
