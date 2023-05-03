package gui.searcher.criterias;

import java.util.Arrays;
import java.util.function.Consumer;

import javax.swing.JLabel;

import evaluators.PlayerEvaluator;
import gui.searcher.SearchCriteriaPanel;
import gui.util.PpmComboBox;
import model.Attributes;
import model.Side;
import searcher.SearchCriteria;
import searcher.criterias.SideSearchCriteria;

public class SideSearchCriteriaPanel<A extends Attributes>
	extends SearchCriteriaPanel<A>
{
	private static final long serialVersionUID = 1617949699372903670L;

	private final JLabel sideLabel;

	private final PpmComboBox<String> sideComboBox;

	public SideSearchCriteriaPanel(
		PlayerEvaluator<A> playerEvaluator,
		Consumer<SearchCriteriaPanel<A>> removeCallback)
	{
		super(playerEvaluator, removeCallback);

		sideLabel = new JLabel("Side:");
		sideComboBox = new PpmComboBox<>(
			Arrays.asList(
				Side.UNIVERSAL.toString(),
				Side.LEFT.toString(),
				Side.RIGHT.toString()));

		centerPanel.add(sideLabel);
		centerPanel.add(sideComboBox);
	}

	@Override
	public String getName()
	{
		return SideSearchCriteria.NAME;
	}

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
