package model;

import util.AbstractModelObject;

public class Attribute
	extends AbstractModelObject
{
	private String shortName;
	private String longName;

	private int rating;
	private int quality;

	public Attribute(String shortName, String longName)
	{
		this.shortName = shortName;
		this.longName = longName;
	}

	private Attribute(String shortName, String longName, int rating, int quality)
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
		return new Attribute(shortName, longName, rating, quality);
	}

	@Override
	public String toString()
	{
		return String.format("%s: %d (%d)", shortName, rating, quality);
	}
}
