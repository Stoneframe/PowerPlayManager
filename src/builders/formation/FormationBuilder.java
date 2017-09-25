package builders.formation;

import model.Attributes;
import model.Formation;
import model.Roster;

public interface FormationBuilder<A extends Attributes, F extends Formation, FT extends FormationTemplate>
{
	public F createFormation(Roster<A> roster, FT template);
}
