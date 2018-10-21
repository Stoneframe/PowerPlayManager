package model;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import util.AbstractModelCollection;
import util.CollectionChangedEvent;

public class Roster<A extends Attributes>
	extends AbstractModelCollection
	implements
		Iterable<Player<A>>
{
	private ObservableList<Player<A>> playerList =
			FXCollections.observableArrayList();

	private ObservableList<Player<A>> umPlayerList =
			FXCollections.unmodifiableObservableList(
				new FilteredList<>(playerList, p -> isEnabled(p)));

	private List<Player<A>> ignored = new LinkedList<>();
	private List<Group> groups = new LinkedList<>();

	public Roster()
	{
	}

	private Roster(List<Player<A>> players)
	{
		this.playerList.addAll(players);
	}

	public ObservableList<Player<A>> getPlayers()
	{
		return umPlayerList;
	}

	public void add(Player<A> player)
	{
		for (Player<A> addedPlayer : playerList)
		{
			if (addedPlayer.equals(player))
			{
				addedPlayer.merge(player);
				return;
			}
		}

		playerList.add(player);
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
		playerList.remove(player);
		groups.forEach(g -> g.players.remove(player));
	}

	public boolean contains(Player<A> player)
	{
		return playerList.contains(player);
	}

	public Player<A> get(int index)
	{
		return playerList.get(index);
	}

	public int indexOf(Object player)
	{
		return playerList.indexOf(player);
	}

	public int size()
	{
		return playerList.size();
	}

	public Roster<A> copy()
	{
		return new Roster<A>(new LinkedList<>(playerList.subList(0, playerList.size())));
	}

	public void clear()
	{
		for (Player<A> player : new LinkedList<>(playerList.subList(0, playerList.size())))
		{
			remove(player);
		}
	}

	public Stream<Player<A>> stream()
	{
		return playerList.stream();
	}

	public List<Player<A>> sort(Comparator<Player<A>> comparator)
	{
		List<Player<A>> copy = new LinkedList<Player<A>>(playerList);

		Collections.sort(copy, comparator);
		Collections.reverse(copy);

		return Collections.unmodifiableList(copy);
	}

	@Override
	public Iterator<Player<A>> iterator()
	{
		return playerList.iterator();
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

		int index = playerList.indexOf(player);

		fireCollectionChanged(CollectionChangedEvent.ADDED, index, player);
	}

	private void disablePlayer(Player<A> player)
	{
		int index = playerList.indexOf(player);

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
