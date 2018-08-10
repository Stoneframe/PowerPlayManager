package model;

import java.util.function.Consumer;
import java.util.function.Supplier;

import util.AbstractModelObject;

public abstract class Attributes
	extends AbstractModelObject
{
	public abstract int getTotalRating();

	public abstract double getAverageQuality();

	public abstract void merge(Attributes attributes);

	protected void mergeAttributeRating(
			Supplier<Integer> thisGet,
			Supplier<Integer> otherGet,
			Consumer<Integer> thisSet)
	{
		if (thisGet.get() < otherGet.get())
		{
			thisSet.accept(otherGet.get());
		}
	}

	protected void mergeAttributeQuality(
			Supplier<Integer> thisGet,
			Supplier<Integer> otherGet,
			Consumer<Integer> thisSet)
	{
		if (otherGet.get() != 0)
		{
			thisSet.accept(otherGet.get());
		}
	}
}