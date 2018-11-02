package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Attribute
{
	private StringProperty name = new SimpleStringProperty();
	private IntegerProperty rating = new SimpleIntegerProperty();
	private IntegerProperty quality = new SimpleIntegerProperty();

	public Attribute(String name)
	{
		setName(name);
	}

	private Attribute(String name, int rating, int quality)
	{
		this(name);

		setRating(rating);
		setQuality(quality);
	}

	public String getName()
	{
		return nameProperty().get();
	}

	public void setName(String name)
	{
		nameProperty().set(name);
	}

	public StringProperty nameProperty()
	{
		return name;
	}

	public int getRating()
	{
		return ratingProperty().get();
	}

	public void setRating(int rating)
	{
		ratingProperty().set(rating);
	}

	public IntegerProperty ratingProperty()
	{
		return rating;
	}

	public int getQuality()
	{
		return qualityProperty().get();
	}

	public void setQuality(int quality)
	{
		qualityProperty().set(quality);
	}

	public IntegerProperty qualityProperty()
	{
		return quality;
	}

	public void merge(Attribute other)
	{
		this.setRating(other.getRating());
		this.setQuality(other.getQuality());
	}

	public Attribute clone()
	{
		return new Attribute(getName(), getRating(), getQuality());
	}

	@Override
	public String toString()
	{
		return String.format("%s: %d (%d)", name, rating, quality);
	}
}
