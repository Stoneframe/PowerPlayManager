package model.evaluators;

import model.PlayerEvaluator;

public class GoaliePlayerEvaluator extends PlayerEvaluator
{
	private static final String NAME = "Goalie";

	private static final int GOA = 100;
	private static final int FIP = 0;
	private static final int SHO = 0;
	private static final int BLK = 75;
	private static final int PAS = 50;
	private static final int TEC = 25;
	private static final int SPE = 25;
	private static final int AGR = 0;

	public GoaliePlayerEvaluator()
	{
		super(NAME, GOA, FIP, SHO, BLK, PAS, TEC, SPE, AGR);
	}
}
