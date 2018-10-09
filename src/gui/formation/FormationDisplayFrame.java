package gui.formation;

import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JFrame;

import formation.Formation;
import model.Attributes;

public class FormationDisplayFrame<A extends Attributes>
	extends JFrame
{
	private static final long serialVersionUID = 291113971084933910L;

	public FormationDisplayFrame(List<Formation<A>> formations)
	{
		super("Formations");

		setLayout(new FlowLayout());

		for (Formation<A> formation : formations)
		{
			add(new FormationPanel<>(formation));
		}

		pack();
	}
}
