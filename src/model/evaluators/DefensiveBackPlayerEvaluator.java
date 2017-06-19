package model.evaluators;

import model.PlayerEvaluator;

public class DefensiveBackPlayerEvaluator extends PlayerEvaluator
{
	private static final String NAME = "Def. Back";

	private static final int GOA = 0;
	private static final int FIP = 100;
	private static final int SHO = 40;
	private static final int BLK = 80;
	private static final int PAS = 80;
	private static final int TEC = 60;
	private static final int SPE = 40;
	private static final int AGR = 40;

	public DefensiveBackPlayerEvaluator()
	{
		super(NAME, GOA, FIP, SHO, BLK, PAS, TEC, SPE, AGR);
	}
}
