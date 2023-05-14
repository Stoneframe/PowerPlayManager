package model;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import evaluators.Weight;
import evaluators.Weights;

public class WeightsTest
{
	private Weights weights;

	private Attributes attributes;

	@Before
	public void setUp()
	{
		String name1 = "name1";
		String name2 = "name2";

		Weight weight1 = new Weight(name1, 75);
		Weight weight2 = new Weight(name2, 25);

		weights = new Weights(Arrays.asList(weight1, weight2));

		Attribute attribute1 = new Attribute(name1, name1);
		Attribute attribute2 = new Attribute(name2, name2);

		attribute1.setRating(60);
		attribute2.setRating(40);

		attributes = new TestAttributes(attribute1, attribute2);
	}

	@Test
	public void calculateRatingForAttributes()
	{

		double expected = 80;
		double actual = weights.getRating(attributes);

		assertEquals(expected, actual, 0);
	}

	private class TestAttributes
		extends Attributes
	{
		protected TestAttributes(Attribute... attributes)
		{
			super(Arrays.asList(attributes));
		}
	}
}
