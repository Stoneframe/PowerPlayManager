package model.evaluators;

import model.PlayerEvaluator;

public class DefensiveWingPlayerEvaluator extends PlayerEvaluator {

	private static final String NAME = "Def. Wing";

	private static final int GOA = 0;
	private static final int FIP = 100;
	private static final int SHO = 25;
	private static final int BLK = 50;
	private static final int PAS = 25;
	private static final int TEC = 50;
	private static final int SEP = 75;
	private static final int AGR = 25;

	public DefensiveWingPlayerEvaluator() {
		super(NAME, GOA, FIP, SHO, BLK, PAS, TEC, SEP, AGR);
	}

}
