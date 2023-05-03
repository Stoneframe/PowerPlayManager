package gui.searcher.criterias;

import java.util.function.Consumer;

import javax.swing.JLabel;
import javax.swing.JTextField;

import evaluators.PlayerEvaluator;
import gui.searcher.SearchCriteriaPanel;
import gui.util.IntegerUtil;
import model.Attributes;
import searcher.SearchCriteria;
import searcher.criterias.ClSearchCriteria;

public class ClSearchCriteriaPanel<A extends Attributes>
	extends SearchCriteriaPanel<A>
{
	private static final long serialVersionUID = 6426953700282529580L;

	private final JLabel minClLabel;
	private final JLabel maxClLabel;

	private final JTextField minClTextField;
	private final JTextField maxClTextField;

	public ClSearchCriteriaPanel(
		PlayerEvaluator<A> playerEvaluator,
		Consumer<SearchCriteriaPanel<A>> removeCallback)
	{
		super(playerEvaluator, removeCallback);

		minClLabel = new JLabel("Min CL:");
		minClTextField = new JTextField(4);

		maxClLabel = new JLabel("Max CL:");
		maxClTextField = new JTextField(4);

		centerPanel.add(minClLabel);
		centerPanel.add(minClTextField);
		centerPanel.add(maxClLabel);
		centerPanel.add(maxClTextField);
	}

	@Override
	public String getName()
	{
		return ClSearchCriteria.NAME;
	}

	@Override
	public ClSearchCriteria<A> getCriteria()
	{
		return new ClSearchCriteria<A>(playerEvaluator, getMinCl(), getMaxCl());
	}

	@Override
	public void clear()
	{
		minClTextField.setText("");
		maxClTextField.setText("");
	}

	@Override
	public void update(SearchCriteria<A> searchCritera)
	{
		setMinCl(((ClSearchCriteria<A>)searchCritera).getMin());
		setMaxCl(((ClSearchCriteria<A>)searchCritera).getMax());
	}

	private int getMinCl()
	{
		return IntegerUtil.parseInt(minClTextField.getText(), Integer.MIN_VALUE);
	}

	private void setMinCl(int cl)
	{
		if (cl == Integer.MIN_VALUE)
		{
			minClTextField.setText("");
			return;
		}

		minClTextField.setText(Integer.toString(cl));
	}

	private int getMaxCl()
	{
		return IntegerUtil.parseInt(maxClTextField.getText(), Integer.MAX_VALUE);
	}

	private void setMaxCl(int cl)
	{
		if (cl == Integer.MAX_VALUE)
		{
			maxClTextField.setText("");
			return;
		}
		maxClTextField.setText(Integer.toString(cl));
	}
}
