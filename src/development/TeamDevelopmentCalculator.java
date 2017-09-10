package development;

import Jama.Matrix;
import model.Roster;

public class TeamDevelopmentCalculator
{
	public static double[] calculateDevelopmentCurve(Roster<?> roster)
	{
		Matrix A = createA(roster);
		Matrix b = createb(roster);

		Matrix x = A.transpose().times(A).inverse().times(A.transpose().times(b));

		return new double[]
		{
				x.get(0, 0), x.get(1, 0), x.get(2, 0)
		};
	}

	private static Matrix createA(Roster<?> roster)
	{
		Matrix A = new Matrix(roster.size(), 3, 1);

		for (int i = 0; i < A.getRowDimension(); i++)
		{
			int age = roster.get(i).getAge();

			A.set(i, 0, age * age);
			A.set(i, 1, age);
		}

		return A;
	}

	private static Matrix createb(Roster<?> roster)
	{
		Matrix b = new Matrix(roster.size(), 1);

		for (int i = 0; i < b.getRowDimension(); i++)
		{
			b.set(i, 0, roster.get(i).getTraining());
		}

		return b;
	}
}
