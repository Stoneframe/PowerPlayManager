package model;

public interface FormationBuilder
{
	public Formation createFormation(Roster roster, FormationTemplate template);

	public Formation createFormation(
			Roster roster,
			String name,
			PlayerEvaluator pivotEvaluator,
			PlayerEvaluator leftWingEvaluator,
			PlayerEvaluator rightWingEvaluator,
			PlayerEvaluator centerBackEvaluator,
			PlayerEvaluator leftBackEvaluator,
			PlayerEvaluator rightBackEvaluator);
}
