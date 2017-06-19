package gui;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;

import model.Player;
import model.PlayerEvaluator;

public abstract class SuggestionPanel extends JPanel
{
	private static final long serialVersionUID = 33853554129162390L;

	private static final int DISPLAYED_POSITIONS_LIMIT = 3;

	public SuggestionPanel(String title)
	{
		setBorder(new CompoundBorder(
				BorderFactory.createTitledBorder(title + " Suggestions"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		setLayout(new GridLayout(DISPLAYED_POSITIONS_LIMIT, 2));
	}

	public void showSuggestions(Player player, List<PlayerEvaluator> evaluators)
	{
		clear();

		for (PlayerEvaluator evaluator : evaluators
				.stream()
				.sorted((a, b) -> compare(player, b, a))
				.limit(DISPLAYED_POSITIONS_LIMIT)
				.toArray(PlayerEvaluator[]::new))
		{
			add(new JLabel(evaluator.getName()));
			add(new JLabel(
					String.format(
						"%.1f",
						getValue(player, evaluator))));
		}

		updateUI();
	}

	protected abstract int compare(
			Player player,
			PlayerEvaluator evaluator1,
			PlayerEvaluator evaluator2);

	protected abstract double getValue(
			Player player,
			PlayerEvaluator evaluator);

	public void clear()
	{
		removeAll();
		updateUI();
	}
}
