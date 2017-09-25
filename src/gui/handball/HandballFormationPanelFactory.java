package gui.handball;

import gui.formation.FormationPanel;
import gui.formation.FormationPanelFactory;
import model.handball.HandballFormation;

public class HandballFormationPanelFactory
	implements
		FormationPanelFactory<HandballFormation>
{
	@Override
	public FormationPanel newInstance(HandballFormation formation)
	{
		return new HandballFormationPanel(formation);
	}
}
