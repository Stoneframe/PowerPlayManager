package formation;

import java.util.List;

import model.Attributes;
import model.Roster;

public interface FormationBuilder<A extends Attributes>
{
	public List<Formation<A>> createFormations(
			Roster<A> roster,
			List<FormationTemplate<A>> formationTemplates,
			boolean considerForm);
}
