package model.evaluators;

import model.PlayerEvaluator;

public class OffensiveBackPlayerEvaluator extends PlayerEvaluator
{
	private static final String NAME = "Off. Back";

	private static final int GOA = 0;
	private static final int FIP = 100;
	private static final int SHO = 100;
	private static final int BLK = 0;
	private static final int PAS = 75;
	private static final int TEC = 50;
	private static final int SPE = 25;
	private static final int AGR = 25;

	public OffensiveBackPlayerEvaluator()
	{
		super(NAME, GOA, FIP, SHO, BLK, PAS, TEC, SPE, AGR);
	}
}
