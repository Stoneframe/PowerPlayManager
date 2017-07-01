package gui.player;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;

import model.Attributes;
import model.PlayerEvaluator;
import model.PropertyChangedEvent;
import model.PropertyChangedListener;

public abstract class SuggestionPanel extends JPanel
		implements
		PropertyChangedListener
{
	private static final long serialVersionUID = 33853554129162390L;

	private static final int DISPLAYED_POSITIONS_LIMIT = 3;

	private Attributes attributes;
	private List<PlayerEvaluator> evaluators;

	public SuggestionPanel(String title)
	{
		setBorder(
			new CompoundBorder(
					BorderFactory.createTitledBorder(title + " Suggestions"),
					BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		setLayout(new GridLayout(DISPLAYED_POSITIONS_LIMIT, 2));
	}

	public void bind(Attributes attributes)
	{
		if (this.attributes != null)
		{
			this.attributes.removePropertyChangedListener(this);
		}

		this.attributes = attributes;

		if (this.attributes != null)
		{
			this.attributes.addPropertyChangedListener(this);
		}

		update();
	}

	public void setPlayerEvaluators(List<PlayerEvaluator> evaluators)
	{
		this.evaluators = evaluators;

		update();
	}

	protected abstract int compare(
			Attributes attributes,
			PlayerEvaluator evaluator1,
			PlayerEvaluator evaluator2);

	protected abstract double getValue(
			Attributes attributes,
			PlayerEvaluator evaluator);

	private void update()
	{
		removeAll();

		if (attributes != null)
		{
			for (PlayerEvaluator evaluator : getSortedEvaluators())
			{
				add(new JLabel(evaluator.getName()));
				add(new JLabel(
						String.format(
							"%.1f",
							getValue(attributes, evaluator))));
			}
		}

		updateUI();
	}

	private PlayerEvaluator[] getSortedEvaluators()
	{
		return evaluators
				.stream()
				.sorted((a, b) -> compare(attributes, b, a))
				.limit(DISPLAYED_POSITIONS_LIMIT)
				.toArray(PlayerEvaluator[]::new);
	}

	@Override
	public void propertyChanged(Object source, PropertyChangedEvent event)
	{
		update();
	}
}
