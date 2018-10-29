package model;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

public class Roster<A extends Attributes>
	// extends AbstractModelCollection
	implements
		Iterable<Player<A>>
{
	private ObservableList<Group> groups = createGroupList();
	private ObservableList<Player<A>> players = createPlayerList();

	private FilteredList<Player<A>> filteredPlayer =
			new FilteredList<>(players, getPredicate(groups));

//	private ObservableList<Player<A>> playerList =
//			FXCollections.observableArrayList();

	// private ObservableList<Player<A>> umPlayerList =
	// FXCollections.unmodifiableObservableList(
	// new FilteredList<>(playerList, p -> isEnabled(p)));

	// private List<Player<A>> ignored = new LinkedList<>();
	// private List<Group> groups = new LinkedList<>();

	public Roster()
	{
	}

	private Roster(List<Player<A>> players)
	{
		this.players.addAll(players);
	}

	public ObservableList<Player<A>> getPlayers()
	{
		return filteredPlayer;
	}

	public ObservableList<Group> getGroups()
	{
		return FXCollections.unmodifiableObservableList(groups);
	}

	// public ObservableList<Player<A>> getPlayers()
	// {
	// return umPlayerList;
	// }

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
		players.remove(player);
		groups.forEach(g -> g.players.remove(player));
	}

	public boolean contains(Player<A> player)
	{
		return players.contains(player);
	}

	public Player<A> get(int index)
	{
		return players.get(index);
	}

	public int indexOf(Object player)
	{
		return players.indexOf(player);
	}

	public int size()
	{
		return players.size();
	}

	public Roster<A> copy()
	{
		return new Roster<A>(new LinkedList<>(players.subList(0, players.size())));
	}

	public void clear()
	{
		for (Player<A> player : new LinkedList<>(players.subList(0, players.size())))
		{
			remove(player);
		}
	}

	public Stream<Player<A>> stream()
	{
		return players.stream();
	}

	public List<Player<A>> sort(Comparator<Player<A>> comparator)
	{
		List<Player<A>> copy = new LinkedList<Player<A>>(players);

		Collections.sort(copy, comparator);
		Collections.reverse(copy);

		return Collections.unmodifiableList(copy);
	}

	@Override
	public Iterator<Player<A>> iterator()
	{
		return players.iterator();
	}

	public Group addGroup(String name, List<Player<A>> players)
	{
		Group group = new Group(name, players);

		groups.add(group);

		return group;
	}

	public void removeGroup(Group group)
	{
		// if (!groups.contains(group)) return;

		groups.remove(group);

		// for (Player<A> player : group.players)
		// {
		// if (shouldBeEnabled(player) && isDisabled(player))
		// {
		// enablePlayer(player);
		// }
		// else if (shouldBeDisabled(player) && isEnabled(player))
		// {
		// disablePlayer(player);
		// }
		// }
	}

	// public List<Group> getGroups()
	// {
	// return Collections.unmodifiableList(groups);
	// }

	private ObservableList<Group> createGroupList()
	{
		ObservableList<Group> groups = FXCollections.observableArrayList(
			group -> new Observable[]
			{
					group.enabledProperty()
			});

		groups.addListener(new ListChangeListener<Group>()
		{
			public void onChanged(Change<? extends Group> c)
			{
				filteredPlayer.setPredicate(getPredicate(groups));
			}
		});

		return groups;
	}

	private ObservableList<Player<A>> createPlayerList()
	{
		return FXCollections.observableArrayList();
	}

	private Predicate<? super Player<A>> getPredicate(ObservableList<Group> groups)
	{
		return p -> groups.stream().allMatch(g -> !g.getPlayers().contains(p))
				|| groups.stream().anyMatch(g -> g.isEnabled() && g.getPlayers().contains(p));
	}

	// private boolean shouldBeEnabled(Player<A> player)
	// {
	// return !isInGroup(player) || isInEnabledGroup(player);
	// }
	//
	// private boolean shouldBeDisabled(Player<A> player)
	// {
	// return isInGroup(player) && !isInEnabledGroup(player);
	// }

	// private boolean isInGroup(Player<A> player)
	// {
	// return groups.stream().anyMatch(g -> g.players.contains(player));
	// }
	//
	// private boolean isInEnabledGroup(Player<A> player)
	// {
	// return groups.stream().anyMatch(g -> g.isEnabled() &&
	// g.players.contains(player));
	// }

	// private boolean isEnabled(Player<A> player)
	// {
	// return !ignored.contains(player);
	// }
	//
	// private boolean isDisabled(Player<A> player)
	// {
	// return ignored.contains(player);
	// }
	//
	// private void enablePlayer(Player<A> player)
	// {
	// ignored.remove(player);
	//
	// int index = playerList.indexOf(player);
	//
	// fireCollectionChanged(CollectionChangedEvent.ADDED, index, player);
	// }
	//
	// private void disablePlayer(Player<A> player)
	// {
	// int index = playerList.indexOf(player);
	//
	// ignored.add(player);
	//
	// fireCollectionChanged(CollectionChangedEvent.REMOVED, index, player);
	// }

	public class Group
	{
		private String name;
		private List<Player<A>> players;

		// private boolean isEnabled = true;

		private BooleanProperty enabled = new SimpleBooleanProperty();

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
			return enabled.get();
		}

		public void setEnabled(boolean isEnabled)
		{
			enabled.set(isEnabled);

			// if (this.isEnabled == isEnabled) return;
			//
			// this.isEnabled = isEnabled;
			//
			// for (Player<A> player : players)
			// {
			// if (isEnabled && ignored.contains(player))
			// {
			// enablePlayer(player);
			// }
			// else if (!isEnabled
			// && !ignored.contains(player)
			// && !isInEnabledGroup(player))
			// {
			// disablePlayer(player);
			// }
			// }
		}

		public BooleanProperty enabledProperty()
		{
			return enabled;
		}

		@Override
		public String toString()
		{
			return getName();
		}
	}
}
