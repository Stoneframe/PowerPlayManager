package model.evaluators;

import model.PlayerEvaluator;

public class DefensivePivotPlayerEvaluator extends PlayerEvaluator {

	private static final String NAME = "Def. Pivot";

	private static final int GOA = 0;
	private static final int FIP = 100;
	private static final int SHO = 25;
	private static final int BLK = 75;
	private static final int PAS = 25;
	private static final int TEC = 50;
	private static final int SEP = 25;
	private static final int AGR = 75;

	public DefensivePivotPlayerEvaluator() {
		super(NAME, GOA, FIP, SHO, BLK, PAS, TEC, SEP, AGR);
	}

}
