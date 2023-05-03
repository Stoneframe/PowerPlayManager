package gui.searcher;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import evaluators.PlayerEvaluator;
import model.Attributes;
import searcher.SearchCriteria;

public abstract class SearchCriteriaPanel<A extends Attributes>
	extends JPanel
{
	private static final long serialVersionUID = -6105265417703531503L;

	protected final PlayerEvaluator<A> playerEvaluator;

	protected final JPanel centerPanel;

	protected final JButton removeButton;

	protected SearchCriteriaPanel(
		PlayerEvaluator<A> playerEvaluator,
		Consumer<SearchCriteriaPanel<A>> removeCallback)
	{
		this.playerEvaluator = playerEvaluator;

		setLayout(new BorderLayout());
		setBorder(BorderFactory.createLineBorder(Color.BLACK));

		centerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		removeButton = new JButton("X");
		removeButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		removeButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				removeCallback.accept(SearchCriteriaPanel.this);
			}
		});

		add(centerPanel, BorderLayout.CENTER);
		add(removeButton, BorderLayout.EAST);
	}
	
	public abstract String getName();

	public abstract SearchCriteria<A> getCriteria();

	public abstract void clear();

	public abstract void update(SearchCriteria<A> searchCritera);
	
	@Override
	public String toString()
	{
		return getName();
	}
}
