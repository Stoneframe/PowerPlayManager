package model;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import util.AbstractModelObject;

public abstract class Attributes
	extends AbstractModelObject
	implements
		Iterable<Attribute>
{
	protected List<Attribute> attributes;

	private ReadOnlyIntegerWrapper totalRating = new ReadOnlyIntegerWrapper();
	private ReadOnlyDoubleWrapper averageQuality = new ReadOnlyDoubleWrapper();

	protected Attributes(List<Attribute> attributes)
	{
		this.attributes = attributes;

		totalRating.bind(
			Bindings.createIntegerBinding(
				() -> attributes.stream().mapToInt(a -> a.getRating()).sum(),
				attributes.stream().map(a -> a.ratingProperty()).toArray(Observable[]::new)));

		averageQuality.bind(
			Bindings.createDoubleBinding(
				() -> attributes.stream().mapToDouble(a -> a.getQuality()).average().getAsDouble(),
				attributes.stream().map(a -> a.qualityProperty()).toArray(Observable[]::new)));
	}

	public int getTotalRating()
	{
		return totalRatingProperty().get();
	}

	public ReadOnlyIntegerProperty totalRatingProperty()
	{
		return totalRating.getReadOnlyProperty();
	}

	public double getAverageQuality()
	{
		return averageQualityProperty().get();
	}

	public ReadOnlyDoubleProperty averageQualityProperty()
	{
		return averageQuality.getReadOnlyProperty();
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
	public String toString()
	{
		StringBuilder builder = new StringBuilder();

		for (Attribute attribute : attributes)
		{
			builder.append(attribute);
			builder.append(System.lineSeparator());
		}

		return builder.toString();
	}
}