package gui.formation;

import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JFrame;

import model.Formation;

public class FormationDisplayFrame<F extends Formation>
	extends JFrame
{
	private static final long serialVersionUID = 291113971084933910L;

	public FormationDisplayFrame(
			FormationPanelFactory<F> formationPanelFactory,
			List<F> formations)
	{
		super("Formations");

		setLayout(new FlowLayout());

		for (F formation : formations)
		{
			add(formationPanelFactory.newInstance(formation));
		}

		pack();
		setVisible(true);
	}
}
