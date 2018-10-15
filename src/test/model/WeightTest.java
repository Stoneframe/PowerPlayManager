package test.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import evaluators.Weight;
import model.Attribute;

public class WeightTest
{
	private Weight weight;
	
	private Attribute attribute;

	@Before
	public void setUp()
	{
		weight = new Weight("name", 50);
		
		attribute = new Attribute("name");
		attribute.setRating(100);
	}

	@Test
	public void calculateRatingFromAttribute()
	{
		double expected = 2;
		double actual = weight.getRating(attribute);

		assertEquals(expected, actual, 0);
	}
}
