package model;

import util.AbstractModelObject;

public class Attribute
	extends AbstractModelObject
{
	private String name;
	private int rating;
	private int quality;

	public Attribute(String name)
	{
		this.name = name;
	}

	private Attribute(String name, int rating, int quality)
	{
		this(name);

		this.rating = rating;
		this.quality = quality;
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

	public int getRating()
	{
		return rating;
	}

	public void setRating(int rating)
	{
		this.rating = rating;
		firePropertyChanged("Rating", rating);
	}

	public int getQuality()
	{
		return quality;
	}

	public void setQuality(int quality)
	{
		this.quality = quality;
		firePropertyChanged("Quality", quality);
	}

	public void merge(Attribute other)
	{
		this.rating = other.rating;
		this.quality = other.quality;
	}

	public Attribute clone()
	{
		return new Attribute(name, rating, quality);
	}

	@Override
	public String toString()
	{
		return String.format("%s: %d (%d)", name, rating, quality);
	}
}
