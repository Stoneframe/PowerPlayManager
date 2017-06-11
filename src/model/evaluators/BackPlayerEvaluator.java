package model.evaluators;

import model.PlayerEvaluator;

public class BackPlayerEvaluator extends PlayerEvaluator
{
	private static final String NAME = "Back";

	private static final int GOA = 0;
	private static final int FIP = 100;
	private static final int SHO = 75;
	private static final int BLK = 25;
	private static final int PAS = 75;
	private static final int TEC = 50;
	private static final int SEP = 25;
	private static final int AGR = 25;

	public BackPlayerEvaluator()
	{
		super(NAME, GOA, FIP, SHO, BLK, PAS, TEC, SEP, AGR);
	}
}
