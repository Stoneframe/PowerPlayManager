package evaluators;

import org.junit.Test;

public class CareerLengthCurveTest
{
	@Test
	public void test()
	{
		CareerLongevityCurve clc = new CareerLongevityCurve(17, 5);

		for (int age = 15; age < 40; age++)
		{
			System.out.println(age + ": " + clc.get(age));
		}
	}
}
