package gui;

import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JFrame;

import model.handball.HandballFormation;

public class FormationDisplayFrame extends JFrame
{
	private static final long serialVersionUID = 291113971084933910L;

	public FormationDisplayFrame(List<HandballFormation> formations)
	{
		super("Formations");

		setLayout(new FlowLayout());

		for (HandballFormation formation : formations)
		{
			add(new FormationPanel(formation));
		}

		pack();
		setVisible(true);
	}
}
