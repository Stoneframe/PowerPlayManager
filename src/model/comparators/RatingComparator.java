package model.comparators;

import java.util.Comparator;

import model.Player;
import model.PlayerEvaluator;

public class RatingComparator implements Comparator<Player> {

	private PlayerEvaluator evaluator;

	public RatingComparator(PlayerEvaluator evaluator) {
		this.evaluator = evaluator;
	}

	@Override
	public int compare(Player o1, Player o2) {
		return Double.compare(
			evaluator.getRating(o1.getAttributes()),
			evaluator.getRating(o2.getAttributes()));
	}

}
