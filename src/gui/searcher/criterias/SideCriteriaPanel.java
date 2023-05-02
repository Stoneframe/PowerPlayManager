package gui.searcher.criterias;

import java.util.Arrays;
import java.util.function.Consumer;

import javax.swing.JLabel;

import evaluators.PlayerEvaluator;
import gui.searcher.CriteriaPanel;
import gui.util.PpmComboBox;
import model.Attributes;
import model.Side;
import searcher.SearchCriteria;
import searcher.criterias.SideSearchCriteria;

public class SideCriteriaPanel<A extends Attributes>
	extends CriteriaPanel<A>
{
	private final JLabel sideLabel;

	private final PpmComboBox<String> sideComboBox;

	public SideCriteriaPanel(
		PlayerEvaluator<A> playerEvaluator,
		Consumer<CriteriaPanel<A>> removeCallback)
	{
		super(playerEvaluator, removeCallback);

		sideLabel = new JLabel("Side:");
		sideComboBox = new PpmComboBox<>(
			Arrays.asList(
				Side.UNIVERSAL.toString(),
				Side.LEFT.toString(),
				Side.RIGHT.toString()));

		add(sideLabel);
		add(sideComboBox);
	}

	public SideCriteriaPanel(
		PlayerEvaluator<A> playerEvaluator,
		Consumer<CriteriaPanel<A>> removeCallback,
		SideSearchCriteria<A> sideSearchCriteria)
	{
		this(playerEvaluator, removeCallback);

		update(sideSearchCriteria);
	}

	private static final long serialVersionUID = 1617949699372903670L;

	@Override
	public SearchCriteria<A> getCriteria()
	{
		return new SideSearchCriteria<>(playerEvaluator, sideComboBox.getSelection());
	}

	@Override
	public void clear()
	{
		sideComboBox.setSelectedIndex(0);
	}

	@Override
	public void update(SearchCriteria<A> searchCritera)
	{
		SideSearchCriteria<A> sideSearchCriteria = (SideSearchCriteria<A>)searchCritera;

		sideComboBox.setSelectedItem(sideSearchCriteria.getSide());
	}
}
