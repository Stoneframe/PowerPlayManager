package model;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
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
		return (int)attributes.stream().mapToDouble(a -> a.getRating()).sum();
	}

	public double getAverageQuality()
	{
		return attributes
			.stream()
			.mapToDouble(a -> a.getQuality())
			.summaryStatistics()
			.getAverage();
	}

	public int getNumberOfAttributes()
	{
		return attributes.size();
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

	@Override
	public int hashCode()
	{
		return Objects.hash(attributes);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj) return true;

		if (obj == null) return false;

		if (getClass() != obj.getClass()) return false;

		Attributes other = (Attributes)obj;

		return Objects.equals(attributes, other.attributes);
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();

		for (Attribute attribute : attributes)
		{
			builder.append(attribute);
			builder.append(System.lineSeparator());
		}
		
		builder.append("Total: ");
		builder.append(getTotalRating());
		builder.append(System.lineSeparator());

		builder.append("Total: ");
		builder.append(getTotalRating());
		builder.append(System.lineSeparator());

		return builder.toString();
	}
}