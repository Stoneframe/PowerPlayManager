package gui.icehockey;

import gui.formation.FormationPanel;
import gui.formation.FormationPanelFactory;
import model.icehockey.IceHockeyFormation;

public class IceHockeyFormationPanelFactory implements FormationPanelFactory<IceHockeyFormation>
{
	@Override
	public FormationPanel newInstance(IceHockeyFormation formation)
	{
		return new IceHockeyFormationPanel(formation);
	}
}
