package model.comparators;

import java.util.Comparator;

import model.Player;
import model.PlayerEvaluator;

public class QualityComparator implements Comparator<Player> {

	private PlayerEvaluator evaluator;

	public QualityComparator(PlayerEvaluator evaluator) {
		this.evaluator = evaluator;
	}

	@Override
	public int compare(Player o1, Player o2) {
		return Double.compare(
			evaluator.getQuality(o1.getAttributes()),
			evaluator.getQuality(o2.getAttributes()));
	}

}
