package model;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
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
			if (addedPlayer.getName().equals(player.getName()))
			{
				addedPlayer.merge(player);

				return;
			}
		}

		players.add(player);

		int index = players.indexOf(player);

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
		int index = players.indexOf(player);

		players.remove(player);

		if (index != -1)
		{
			fireCollectionChanged(CollectionChangedEvent.REMOVED, index, player);
		}
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
		return new Roster<A>(players);
	}

	public Roster<A> copy(Predicate<Player<A>> filter)
	{
		return new Roster<A>(players.stream().filter(filter).collect(Collectors.toList()));
	}

	public void clear()
	{
		while (!players.isEmpty())
		{
			remove(players.get(0));
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

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();

		for (Player<A> player : players)
		{
			builder.append(player);
			builder.append(System.lineSeparator());
		}

		return builder.toString();
	}
}
