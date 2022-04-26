package model;

import java.util.LinkedList;
import java.util.List;

public class Groups<A extends Attributes>
{
	private final List<Group> groups = new LinkedList<>();

	public Group add(String name, List<Player<A>> players)
	{
		Group group = new Group(name, players);

		groups.add(group);

		return group;
	}

	public void remove(Group group)
	{
		groups.remove(group);
	}

	public boolean isEnabled(Player<A> player)
	{
		boolean inDisabledGroup = groups.stream()
			.filter(g -> !g.isEnabled())
			.anyMatch(g -> g.getPlayers().contains(player));

		boolean inEnabledGroup = groups.stream()
			.filter(g -> g.isEnabled())
			.anyMatch(g -> g.getPlayers().contains(player));

		return !inDisabledGroup || inEnabledGroup;
	}

	public void remove(Player<A> player)
	{
		groups.stream()
			.filter(g -> g.getPlayers().contains(player))
			.forEach(g -> g.getPlayers().remove(player));
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
			this.isEnabled = isEnabled;
		}

		@Override
		public String toString()
		{
			return getName();
		}
	}
}
