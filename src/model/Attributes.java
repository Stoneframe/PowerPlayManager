package model;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import util.AbstractModelObject;

public abstract class Attributes
	extends AbstractModelObject
	implements
		Iterable<Attribute>
{
	protected List<Attribute> attributes;

	protected Attributes(List<Attribute> attributes)
	{
		this.attributes = attributes;
	}

	public int getTotalRating()
	{
		return attributes.stream().mapToInt(a -> a.getRating()).sum();
	}

	public double getAverageQuality()
	{
		return attributes
			.stream()
			.mapToDouble(a -> a.getQuality())
			.summaryStatistics()
			.getAverage();
	}

	public void merge(Attributes other)
	{
		for (int i = 0; i < this.attributes.size(); i++)
		{
			this.attributes.get(i).merge(other.attributes.get(i));
		}

		firePropertyChanged("All", null);
	}

	public Stream<Attribute> stream()
	{
		return attributes.stream();
	}

	@Override
	public Iterator<Attribute> iterator()
	{
		return attributes.iterator();
	}
}