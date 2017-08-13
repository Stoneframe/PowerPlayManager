package gui.formation;

import model.Formation;

public interface FormationPanelFactory<F extends Formation>
{
	public FormationPanel newInstance(F formation);
}
