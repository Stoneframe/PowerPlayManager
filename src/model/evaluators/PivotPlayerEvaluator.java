package model.evaluators;

import model.PlayerEvaluator;

public class PivotPlayerEvaluator extends PlayerEvaluator
{
	private static final String NAME = "Pivot";

	private static final int GOA = 0;
	private static final int FIP = 100;
	private static final int SHO = 60;
	private static final int BLK = 60;
	private static final int PAS = 40;
	private static final int TEC = 60;
	private static final int SPE = 40;
	private static final int AGR = 80;

	public PivotPlayerEvaluator()
	{
		super(NAME, GOA, FIP, SHO, BLK, PAS, TEC, SPE, AGR);
	}
}
