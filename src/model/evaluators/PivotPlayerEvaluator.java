package model.evaluators;

import model.PlayerEvaluator;

public class PivotPlayerEvaluator extends PlayerEvaluator
{
	private static final String NAME = "Pivot";

	private static final int GOA = 0;
	private static final int FIP = 100;
	private static final int SHO = 50;
	private static final int BLK = 50;
	private static final int PAS = 25;
	private static final int TEC = 50;
	private static final int SPE = 25;
	private static final int AGR = 75;

	public PivotPlayerEvaluator()
	{
		super(NAME, GOA, FIP, SHO, BLK, PAS, TEC, SPE, AGR);
	}
}
