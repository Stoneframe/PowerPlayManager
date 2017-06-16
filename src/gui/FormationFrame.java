package gui;

import java.awt.FlowLayout;

import javax.swing.JFrame;

import model.Formation;

public class FormationFrame extends JFrame
{
	private static final long serialVersionUID = 291113971084933910L;

	public FormationFrame(Formation... formations)
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
