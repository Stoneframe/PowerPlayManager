package evaluators;

public class FacilityEvaluator
{
	public double getOverallEffectivness(int facilityLevel, double staffEffectivness)
	{
		return getFacilityEffectivness(facilityLevel) * (1 + staffEffectivness / 200);
	}

	private static double getFacilityEffectivness(int facilityLevel)
	{
		return 0.5 * Math.pow(facilityLevel, 2) + 0.5 * Math.pow(facilityLevel, 1) + 3;
	}
}
