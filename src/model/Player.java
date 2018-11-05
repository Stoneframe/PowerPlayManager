package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import util.AbstractModelObject;

public class Player<A extends Attributes>
	extends AbstractModelObject
{
	protected A attributes;

	private final StringProperty name = new SimpleStringProperty();
	private final IntegerProperty age = new SimpleIntegerProperty();
	private final IntegerProperty cl = new SimpleIntegerProperty();
	private final ObjectProperty<Side> side = new SimpleObjectProperty<>();

	private final IntegerProperty experience = new SimpleIntegerProperty();
	private final IntegerProperty chemistry = new SimpleIntegerProperty();
	private final IntegerProperty energy = new SimpleIntegerProperty();

	private final DoubleProperty training = new SimpleDoubleProperty();

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
		setName(name);
		setAge(age);
		setCl(cl);
		setSide(side);
		this.attributes = attributes;
		this.attributes.addPropertyChangedListener((s, e) -> firePropertyChanged(e));
		setExperience(experience);
		setChemistry(chemistry);
		setEnergy(energy);
		setTraining(training);
	}

	public String getName()
	{
		return nameProperty().get();
	}

	public void setName(String name)
	{
		nameProperty().set(name);
		firePropertyChanged("Name", name);
	}

	public StringProperty nameProperty()
	{
		return name;
	}

	public int getAge()
	{
		return ageProperty().get();
	}

	public void setAge(int age)
	{
		ageProperty().set(age);
		firePropertyChanged("Age", age);
	}

	public IntegerProperty ageProperty()
	{
		return age;
	}

	public int getCl()
	{
		return clProperty().get();
	}

	public void setCl(int cl)
	{
		clProperty().set(cl);
		firePropertyChanged("CL", cl);
	}

	public IntegerProperty clProperty()
	{
		return cl;
	}

	public Side getSide()
	{
		return sideProperty().get();
	}

	public void setSide(Side side)
	{
		sideProperty().set(side);
		firePropertyChanged("Side", side);
	}

	public ObjectProperty<Side> sideProperty()
	{
		return side;
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
		return trainingProperty().get();
	}

	public void setTraining(double training)
	{
		trainingProperty().set(training);
		firePropertyChanged("Training", training);
	}

	public DoubleProperty trainingProperty()
	{
		return training;
	}

	public void merge(Player<A> other)
	{
		if (!this.equals(other)) return;

		if (other.getAge() > this.getAge())
		{
			this.setAge(other.getAge());
		}

		if (this.getCl() == -1)
		{
			this.setCl(other.getCl());
		}

		if (this.getSide() == Side.UNKNOWN)
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

		return this.getName().equals(other.getName());
	}

	@Override
	public String toString()
	{
		return String.format("%s (%s)\n%s", name, side, attributes);
	}
}
