package gui.player;

import java.awt.GridLayout;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;

import evaluators.AttributeEvaluator;
import model.Attributes;
import util.PropertyChangedEvent;
import util.PropertyChangedListener;

public abstract class SuggestionPanel<A extends Attributes>
	extends JPanel
	implements
		PropertyChangedListener
{
	private static final long serialVersionUID = 33853554129162390L;

	private static final int DISPLAYED_POSITIONS_LIMIT = 3;

	private A attributes;
	private List<AttributeEvaluator<A>> evaluators;

	public SuggestionPanel(String title)
	{
		setBorder(
			new CompoundBorder(
					BorderFactory.createTitledBorder(title + " Suggestions"),
					BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		setLayout(new GridLayout(DISPLAYED_POSITIONS_LIMIT, 2));
	}

	public void bind(A attributes)
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

	public void setAttributeEvaluators(List<AttributeEvaluator<A>> evaluators)
	{
		this.evaluators = evaluators;

		update();
	}

	protected abstract int compare(
			A attributes,
			AttributeEvaluator<A> evaluator1,
			AttributeEvaluator<A> evaluator2);

	protected abstract double getValue(
			A attributes,
			AttributeEvaluator<A> evaluator);

	private void update()
	{
		removeAll();

		if (attributes != null)
		{
			for (AttributeEvaluator<A> evaluator : getSortedEvaluators())
			{
				add(new JLabel(evaluator.getName()));
				add(new JLabel(String.format("%.1f", getValue(attributes, evaluator))));
			}
		}

		updateUI();
	}

	private List<AttributeEvaluator<A>> getSortedEvaluators()
	{
		return evaluators
				.stream()
				.sorted((a, b) -> compare(attributes, b, a))
				.limit(DISPLAYED_POSITIONS_LIMIT)
				.collect(Collectors.toList());
	}

	@Override
	public void propertyChanged(Object source, PropertyChangedEvent event)
	{
		update();
	}
}
