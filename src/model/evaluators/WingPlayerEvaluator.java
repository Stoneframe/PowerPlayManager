package model.evaluators;

import model.PlayerEvaluator;

public class WingPlayerEvaluator extends PlayerEvaluator
{
	private static final String NAME = "Wing";

	private static final int GOA = 0;
	private static final int FIP = 100;
	private static final int SHO = 60;
	private static final int BLK = 40;
	private static final int PAS = 40;
	private static final int TEC = 60;
	private static final int SPE = 80;
	private static final int AGR = 40;

	public WingPlayerEvaluator()
	{
		super(NAME, GOA, FIP, SHO, BLK, PAS, TEC, SPE, AGR);
	}
}
