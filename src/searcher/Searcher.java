package searcher;

import java.util.List;
import java.util.stream.Collectors;

import model.Attributes;
import model.Player;
import model.Roster;

public class Searcher<A extends Attributes>
{
	private final Roster<A> roster;
	private final List<SearchCriteria<A>> criterias;

	public Searcher(Roster<A> roster, List<SearchCriteria<A>> criterias)
	{
		this.roster = roster;
		this.criterias = criterias;
	}

	public List<Player<A>> search()
	{
		return roster.stream()
			.filter(p -> criterias.stream().allMatch(c -> c.check(p)))
			.collect(Collectors.toList());
	}
}
