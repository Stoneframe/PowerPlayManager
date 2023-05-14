package calendar;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public abstract class Calendar
{
	private static final int DAYS_PER_SEASON = 112;

	private final LocalDate start;

	protected Calendar(LocalDate start)
	{
		this.start = start;
	}

	public int getSeason(LocalDate date)
	{
		return (int)(ChronoUnit.DAYS.between(start, date) / 112) + 1;
	}

	public int getSeason()
	{
		return getSeason(LocalDate.now());
	}

	public int getSeasonDay(LocalDate date)
	{
		return (int)(ChronoUnit.DAYS.between(start, date) % 112) + 1;
	}

	public int getSeasonDay()
	{
		return getSeasonDay(LocalDate.now());
	}

	public int getDaysRemainingInSeasons(int season)
	{
		if (season == getSeason())
		{
			return DAYS_PER_SEASON - getSeasonDay();
		}

		return DAYS_PER_SEASON;
	}
}
