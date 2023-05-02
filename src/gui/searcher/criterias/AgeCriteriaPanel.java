package gui.searcher.criterias;

import java.util.function.Consumer;

import javax.swing.JLabel;
import javax.swing.JTextField;

import evaluators.PlayerEvaluator;
import gui.searcher.CriteriaPanel;
import gui.util.IntegerUtil;
import model.Attributes;
import searcher.SearchCriteria;
import searcher.criterias.AgeSearchCriteria;

public class AgeCriteriaPanel<A extends Attributes>
	extends CriteriaPanel<A>
{
	private static final long serialVersionUID = 6426953700282529580L;

	private final JLabel minAgeLabel;
	private final JLabel maxAgeLabel;

	private final JTextField minAgeTextField;
	private final JTextField maxAgeTextField;

	public AgeCriteriaPanel(
		PlayerEvaluator<A> playerEvaluator,
		Consumer<CriteriaPanel<A>> removeCallback,
		AgeSearchCriteria<A> criteria)
	{
		this(playerEvaluator, removeCallback);

		update(criteria);
	}

	public AgeCriteriaPanel(
		PlayerEvaluator<A> playerEvaluator,
		Consumer<CriteriaPanel<A>> removeCallback)
	{
		super(playerEvaluator, removeCallback);

		minAgeLabel = new JLabel("Min age:");
		minAgeTextField = new JTextField(4);

		maxAgeLabel = new JLabel("Max age:");
		maxAgeTextField = new JTextField(4);

		add(minAgeLabel);
		add(minAgeTextField);
		add(maxAgeLabel);
		add(maxAgeTextField);
	}

	@Override
	public AgeSearchCriteria<A> getCriteria()
	{
		return new AgeSearchCriteria<A>(playerEvaluator, getMinAge(), getMaxAge());
	}

	@Override
	public void clear()
	{
		minAgeTextField.setText("");
		maxAgeTextField.setText("");
	}

	@Override
	public void update(SearchCriteria<A> searchCritera)
	{
		setMinAge(((AgeSearchCriteria<A>)searchCritera).getMin());
		setMaxAge(((AgeSearchCriteria<A>)searchCritera).getMax());
	}

	private int getMinAge()
	{
		return IntegerUtil.parseInt(minAgeTextField.getText(), Integer.MIN_VALUE);
	}

	private void setMinAge(int age)
	{
		if (age == Integer.MIN_VALUE)
		{
			minAgeTextField.setText("");
			return;
		}

		minAgeTextField.setText(Integer.toString(age));
	}

	private int getMaxAge()
	{
		return IntegerUtil.parseInt(maxAgeTextField.getText(), Integer.MAX_VALUE);
	}

	private void setMaxAge(int age)
	{
		if (age == Integer.MAX_VALUE)
		{
			maxAgeTextField.setText("");
			return;
		}
		maxAgeTextField.setText(Integer.toString(age));
	}
}
