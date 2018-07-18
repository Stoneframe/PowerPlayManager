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
	private List<Player<A>> ignored = new LinkedList<>();
	private List<Group> groups = new LinkedList<>();

	public Roster()
	{
	}

	private Roster(List<Player<A>> players)
	{
		this.players.addAll(players);
	}

	public void add(Player<A> player)
	{
		for (Player<A> addedPlayer : players)
		{
			if (addedPlayer.equals(player))
			{
				addedPlayer.merge(player);

				return;
			}
		}

		players.add(player);

		int index = getFilteredPlayersList().indexOf(player);

		if (index != -1)
		{
			fireCollectionChanged(CollectionChangedEvent.ADDED, index, player);
		}
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
		groups.forEach(g -> g.players.remove(player));

		if (index != -1)
		{
			fireCollectionChanged(CollectionChangedEvent.REMOVED, index, player);
		}
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

	public Group addGroup(String name, List<Player<A>> players)
	{
		Group group = new Group(name, players);

		groups.add(group);

		return group;
	}

	public void removeGroup(Group group)
	{
		if (!groups.contains(group)) return;

		groups.remove(group);

		for (Player<A> player : group.players)
		{
			if (shouldBeEnabled(player) && isDisabled(player))
			{
				enablePlayer(player);
			}
			else if (shouldBeDisabled(player) && isEnabled(player))
			{
				disablePlayer(player);
			}
		}
	}

	public List<Group> getGroups()
	{
		return Collections.unmodifiableList(groups);
	}

	public String toJson()
	{
		String format = "{'players': [%s]}".replace('\'', '"');

		return String.format(
			format,
			String.join(",", players.stream().map(p -> p.toJson()).collect(Collectors.toList())));
	}

	private List<Player<A>> getFilteredPlayersList()
	{
		return players
			.stream()
			.filter(p -> isEnabled(p))
			.collect(Collectors.toList());
	}

	private boolean shouldBeEnabled(Player<A> player)
	{
		return !isInGroup(player) || isInEnabledGroup(player);
	}

	private boolean shouldBeDisabled(Player<A> player)
	{
		return isInGroup(player) && !isInEnabledGroup(player);
	}

	private boolean isInGroup(Player<A> player)
	{
		return groups.stream().anyMatch(g -> g.players.contains(player));
	}

	private boolean isInEnabledGroup(Player<A> player)
	{
		return groups.stream().anyMatch(g -> g.isEnabled && g.players.contains(player));
	}

	private boolean isEnabled(Player<A> player)
	{
		return !ignored.contains(player);
	}

	private boolean isDisabled(Player<A> player)
	{
		return ignored.contains(player);
	}

	private void enablePlayer(Player<A> player)
	{
		ignored.remove(player);

		int index = getFilteredPlayersList().indexOf(player);

		fireCollectionChanged(CollectionChangedEvent.ADDED, index, player);
	}

	private void disablePlayer(Player<A> player)
	{
		int index = getFilteredPlayersList().indexOf(player);

		ignored.add(player);

		fireCollectionChanged(CollectionChangedEvent.REMOVED, index, player);
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

		public void setName(String name)
		{
			this.name = name;
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
			if (this.isEnabled == isEnabled) return;

			this.isEnabled = isEnabled;

			for (Player<A> player : players)
			{
				if (isEnabled && ignored.contains(player))
				{
					enablePlayer(player);
				}
				else if (!isEnabled
						&& !ignored.contains(player)
						&& !isInEnabledGroup(player))
				{
					disablePlayer(player);
				}
			}
		}

		@Override
		public String toString()
		{
			return getName();
		}
	}
}
