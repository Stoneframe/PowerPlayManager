package model.builders;

import model.Formation;
import model.FormationBuilder;
import model.PlayerEvaluator;
import model.Rooster;
import model.Side;

public class DumbFormationBuilder implements FormationBuilder
{
	@Override
	public Formation create(
			Rooster rooster,
			PlayerEvaluator pivotEvaluator,
			PlayerEvaluator leftWingEvaluator,
			PlayerEvaluator rightWingEvaluator,
			PlayerEvaluator centerBackEvaluator,
			PlayerEvaluator leftBackEvaluator,
			PlayerEvaluator rightBackEvaluator)
	{
		return new Formation(
				rooster.select(pivotEvaluator, Side.UNIVERSAL),
				rooster.select(leftWingEvaluator, Side.LEFT),
				rooster.select(rightWingEvaluator, Side.RIGHT),
				rooster.select(centerBackEvaluator, Side.UNIVERSAL),
				rooster.select(leftBackEvaluator, Side.LEFT),
				rooster.select(rightBackEvaluator, Side.RIGHT));
	}
}
