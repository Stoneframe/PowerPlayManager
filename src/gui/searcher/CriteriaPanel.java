package gui.searcher;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;

import javax.swing.JButton;
import javax.swing.JPanel;

import evaluators.PlayerEvaluator;
import model.Attributes;
import searcher.SearchCriteria;

public abstract class CriteriaPanel<A extends Attributes>
	extends JPanel
{
	private static final long serialVersionUID = -6105265417703531503L;

	protected final PlayerEvaluator<A> playerEvaluator;

	protected CriteriaPanel(
		PlayerEvaluator<A> playerEvaluator,
		Consumer<CriteriaPanel<A>> removeCallback)
	{
		this.playerEvaluator = playerEvaluator;
		
		setLayout(new FlowLayout());

		JButton button = new JButton("X");

		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					removeCallback.accept(CriteriaPanel.this);
				}
				catch (Exception e2)
				{
					e2.printStackTrace();
				}
			}
		});

		add(button);
	}

	public abstract SearchCriteria<A> getCriteria();

	public abstract void clear();

	public abstract void update(SearchCriteria<A> searchCritera);
}
