package formation;

import java.util.List;

import model.Attributes;
import model.Formation;
import model.Roster;

public interface FormationBuilder<
		A extends Attributes,
		F extends Formation,
		FT extends FormationTemplate<A>>
{
	public List<F> createFormations(Roster<A> roster, List<FT> formationTemplates);
}
