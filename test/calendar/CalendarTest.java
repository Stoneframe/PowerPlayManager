package calendar;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

import calendar.football.FootballCalendar;
import calendar.handball.HandballCalendar;
import calendar.icehockey.IceHockeyCalendar;

public class CalendarTest
{
	@Test
	public void getSeasonDay_isFirstDay_return1()
	{
		Calendar calendar = new TestCalendar();

		int day = calendar.getSeasonDay(LocalDate.of(2023, 1, 1));

		assertEquals(1, day);
	}

	@Test
	public void getSeasonDay_isLastDay_return112()
	{
		Calendar calendar = new TestCalendar();

		int day = calendar.getSeasonDay(LocalDate.of(2023, 4, 22));

		assertEquals(112, day);
	}

	@Test
	public void getSeasonDay_isFirstDayNextSeason_return1()
	{
		Calendar calendar = new TestCalendar();

		int day = calendar.getSeasonDay(LocalDate.of(2023, 4, 23));

		assertEquals(1, day);
	}

	@Test
	public void getSeasonDay_isFootballCalendarAndDateIs20230512_return19()
	{
		Calendar calendar = new FootballCalendar();

		int day = calendar.getSeasonDay(LocalDate.of(2023, 5, 12));

		assertEquals(19, day);
	}

	@Test
	public void getSeasonDay_isHandballCalendarAndDateIs20230512_return54()
	{
		Calendar calendar = new HandballCalendar();

		int day = calendar.getSeasonDay(LocalDate.of(2023, 5, 12));

		assertEquals(54, day);
	}

	@Test
	public void getSeasonDay_isIceHockeyCalendarAndDateIs20230512_return96()
	{
		Calendar calendar = new IceHockeyCalendar();

		int day = calendar.getSeasonDay(LocalDate.of(2023, 5, 12));

		assertEquals(96, day);
	}

	@Test
	public void getSeason_isFootballCalendarAndDateIs20230512_return43()
	{
		Calendar calendar = new FootballCalendar();

		int season = calendar.getSeason(LocalDate.of(2023, 5, 12));

		assertEquals(43, season);
	}

	@Test
	public void getSeason_isHandballCalendarAndDateIs20230512_return33()
	{
		Calendar calendar = new HandballCalendar();

		int season = calendar.getSeason(LocalDate.of(2023, 5, 12));

		assertEquals(33, season);
	}

	@Test
	public void getSeason_isIceHockeyCalendarAndDateIs20230512_return46()
	{
		Calendar calendar = new IceHockeyCalendar();

		int season = calendar.getSeason(LocalDate.of(2023, 5, 12));

		assertEquals(46, season);
	}

	private class TestCalendar
		extends Calendar
	{
		public TestCalendar(LocalDate start)
		{
			super(start);
		}

		public TestCalendar()
		{
			this(LocalDate.of(2023, 1, 1));
		}
	}
}
