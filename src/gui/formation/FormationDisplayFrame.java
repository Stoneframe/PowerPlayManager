package gui.formation;

import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JFrame;

import model.Formation;

public class FormationDisplayFrame extends JFrame
{
	private static final long serialVersionUID = 291113971084933910L;

	public FormationDisplayFrame(List<Formation> formations)
	{
		super("Formations");

		setLayout(new FlowLayout());

		for (Formation formation : formations)
		{
			add(new FormationPanel(formation));
		}

		pack();
		setVisible(true);
	}
}
