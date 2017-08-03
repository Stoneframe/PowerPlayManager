package model;

import evaluators.PlayerEvaluator;
import model.handball.HandballAttributes;

public interface HandballFormationBuilder
{
	public HandballFormation createFormation(
			Roster<HandballAttributes> roster,
			HandballFormationTemplate template);

	public HandballFormation createFormation(
			Roster<HandballAttributes> roster,
			String name,
			PlayerEvaluator<HandballAttributes> pivotEvaluator,
			PlayerEvaluator<HandballAttributes> leftWingEvaluator,
			PlayerEvaluator<HandballAttributes> rightWingEvaluator,
			PlayerEvaluator<HandballAttributes> centerBackEvaluator,
			PlayerEvaluator<HandballAttributes> leftBackEvaluator,
			PlayerEvaluator<HandballAttributes> rightBackEvaluator);
}
