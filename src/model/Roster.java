package model;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import util.AbstractModelCollection;
import util.CollectionChangedEvent;

public class Roster<A extends Attributes>
	extends AbstractModelCollection
	implements
		Iterable<Player<A>>
{
	private List<Player<A>> players = new LinkedList<>();
	private List<Group> groups = new LinkedList<>();

	public Roster()
	{
	}

	private Roster(List<Player<A>> players)
	{
		this.getFilteredPlayersList().addAll(players);
	}

	public void add(Player<A> player)
	{
		for (Player<A> addedPlayer : getFilteredPlayersList())
		{
			if (addedPlayer.equals(player))
			{
				addedPlayer.merge(player);

				return;
			}
		}

		players.add(player);

		fireCollectionChanged(
			CollectionChangedEvent.ADDED,
			getFilteredPlayersList().indexOf(player),
			player);
	}

	public void addAll(List<Player<A>> players)
	{
		for (Player<A> player : players)
		{
			add(player);
		}
	}

	public void remove(Player<A> player)
	{
		int index = getFilteredPlayersList().indexOf(player);

		players.remove(player);

		fireCollectionChanged(CollectionChangedEvent.REMOVED, index, player);
	}

	public boolean contains(Player<A> player)
	{
		return getFilteredPlayersList().contains(player);
	}

	public Player<A> get(int index)
	{
		return getFilteredPlayersList().get(index);
	}

	public int indexOf(Object player)
	{
		return getFilteredPlayersList().indexOf(player);
	}

	public int size()
	{
		return getFilteredPlayersList().size();
	}

	public Roster<A> copy()
	{
		return new Roster<A>(getFilteredPlayersList());
	}

	public void clear()
	{
		while (!getFilteredPlayersList().isEmpty())
		{
			remove(getFilteredPlayersList().get(0));
		}
	}

	public Stream<Player<A>> stream()
	{
		return getFilteredPlayersList().stream();
	}

	public List<Player<A>> sort(Comparator<Player<A>> comparator)
	{
		List<Player<A>> copy = new LinkedList<Player<A>>(getFilteredPlayersList());

		Collections.sort(copy, comparator);
		Collections.reverse(copy);

		return Collections.unmodifiableList(copy);
	}

	@Override
	public Iterator<Player<A>> iterator()
	{
		return getFilteredPlayersList().iterator();
	}

	public void addGroup(String name, List<Player<A>> players)
	{
		groups.add(new Group(name, players));
	}

	public void removeGroup(String name)
	{
		Group group = getGroup(name);

		groups.remove(group);
	}

	public void setGroupEnabled(String name, boolean isEnabled)
	{
		Group group = getGroup(name);

		group.isEnabled = isEnabled;
	}

	private Group getGroup(String name)
	{
		return groups
				.stream()
				.filter(g -> g.name.equals(name))
				.findFirst()
				.orElse(null);
	}

	private List<Player<A>> getFilteredPlayersList()
	{
		return players
				.stream()
				.filter(p -> groups.stream().allMatch(g -> g.isEnabled || !g.players.contains(p)))
				.collect(Collectors.toList());
	}

	public class Group
	{
		private String name;
		private List<Player<A>> players;
		private boolean isEnabled = true;

		public Group(String name, List<Player<A>> players)
		{
			this.name = name;
			this.players = players;
		}

		public String getName()
		{
			return name;
		}

		public List<Player<A>> getPlayers()
		{
			return players;
		}

		public boolean isEnabled()
		{
			return isEnabled;
		}

		public void setEnabled(boolean isEnabled)
		{
			this.isEnabled = isEnabled;
		}
	}
}
