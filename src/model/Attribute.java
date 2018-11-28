package model;

import util.AbstractModelObject;

public class Attribute
	extends AbstractModelObject
{
	private String shortName;
	private String longName;

	private double rating;
	private int quality;

	public Attribute(String shortName, String longName)
	{
		this.shortName = shortName;
		this.longName = longName;
	}

	private Attribute(String shortName, String longName, double rating, int quality)
	{
		this(shortName, longName);

		this.rating = rating;
		this.quality = quality;
	}

	public String getShortName()
	{
		return shortName;
	}

	public void setShortName(String shortName)
	{
		this.shortName = shortName;
		firePropertyChanged("ShortNme", shortName);
	}

	public String getLongName()
	{
		return longName;
	}

	public void setLongName(String longName)
	{
		this.longName = longName;
		firePropertyChanged("LongNme", longName);
	}

	public int getRating()
	{
		return (int)rating;
	}

	public void setRating(int rating)
	{
		this.rating = Math.max(rating, 1);
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

	public void addToRating(double value)
	{
		rating = Math.max(rating + value, 1);
		firePropertyChanged("Rating", rating);
	}

	public void merge(Attribute other)
	{
		this.rating = other.rating;
		this.quality = other.quality;
	}

	public Attribute clone()
	{
		return new Attribute(shortName, longName, rating, quality);
	}

	@Override
	public String toString()
	{
		return String.format("%s: %d (%d)", shortName, rating, quality);
	}
}
