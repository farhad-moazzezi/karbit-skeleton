package org.karbit.convertor;

import java.text.ParseException;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.TimeUnit;
import com.ibm.icu.util.ULocale;

public final class DateConvertor {

	public static final String DEFAULT_DATE_PATTERN = "yyyy/MM/dd";

	public static final String PERSIAN_DATE_TIME_PATTERN = "yyyy/MM/dd HH:mm:ss";

	public static final String DEFAULT_LOCALE = "fa_IR@calendar=persian";

	public static final String ENGLISH_DIGIT_PERSIAN_LOCALE = "en_US@calendar=persian";

	private DateConvertor() {

	}

	public static Date addDayToDate(Date date, int days) {
		return DateConvertor.addDurationToDate(date, days, ChronoUnit.DAYS);
	}

	public static Instant addDayToDate(Instant date, int days) {
		return DateConvertor.addDurationToDate(date, days, ChronoUnit.DAYS);
	}

	public static Date addHourToDate(Date date, int hours) {
		return DateConvertor.addDurationToDate(date, hours, ChronoUnit.HOURS);
	}

	public static Instant addHourToDate(Instant date, int hours) {
		return DateConvertor.addDurationToDate(date, hours, ChronoUnit.HOURS);
	}

	public static Date addMinuteToDate(Date date, int minutes) {
		return DateConvertor.addDurationToDate(date, minutes, ChronoUnit.MINUTES);
	}

	public static Instant addMinuteToDate(Instant date, int minutes) {
		return DateConvertor.addDurationToDate(date, minutes, ChronoUnit.MINUTES);
	}

	public static Date addPersianMonth(Date date, int months) {
		com.ibm.icu.util.Calendar calendar = com.ibm.icu.util.Calendar.getInstance(new ULocale(DEFAULT_LOCALE));
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, months);
		return calendar.getTime();
	}

	public static Date addDurationToDate(Date date, int duration, ChronoUnit unit) {
		return Date.from(date.toInstant().plus(Duration.of(duration, unit)));
	}

	public static Instant addDurationToDate(Instant date, int duration, ChronoUnit unit) {
		return date.plus(Duration.of(duration, unit));
	}

	public static String toPersianDate(Date date) {
		return toPersianDate(date, DEFAULT_DATE_PATTERN);
	}

	public static String toPersianDate(Date date, String pattern) {
		return toPersianDate(date, pattern, DEFAULT_LOCALE);
	}

	public static String toPersianDate(Date date, String pattern, String locale) {
		DateFormat df = new SimpleDateFormat(pattern, new ULocale(locale));
		return df.format(date);
	}

	public static String toEnglishDate(Date date, String pattern) {
		DateFormat df = new SimpleDateFormat(pattern, ULocale.getDefault());
		return df.format(date);
	}

	public static String toDigitEngPersianDate(Date date) {
		return toDigitEngPersianDate(date, DEFAULT_DATE_PATTERN);
	}

	public static String toDigitEngPersianDate(Date date, String pattern) {
		return toDigitEngPersianDate(date, pattern, ENGLISH_DIGIT_PERSIAN_LOCALE);
	}

	public static String toDigitEngPersianDate(Date date, String pattern, String locale) {
		DateFormat df = new SimpleDateFormat(pattern, new Locale(locale));
		return df.format(date);
	}

	public static Date fromPersianDate(String date) throws ParseException {
		return fromPersianDate(date, PERSIAN_DATE_TIME_PATTERN);
	}

	public static Date fromPersianDate(String date, String pattern) throws ParseException {
		return fromPersianDate(date, pattern, DEFAULT_LOCALE);
	}

	public static Date fromPersianDate(String date, String pattern, String locale) throws ParseException {
		DateFormat df = new SimpleDateFormat(pattern, new Locale(locale));
		return df.parse(date);
	}

	public static Date getFirstDayOfMonth(Date date) {
		com.ibm.icu.util.Calendar calendar = com.ibm.icu.util.Calendar.getInstance(new ULocale(DEFAULT_LOCALE));
		calendar.setTime(date);
		calendar.set(com.ibm.icu.util.Calendar.DAY_OF_MONTH,
				calendar.getActualMinimum(com.ibm.icu.util.Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}

	public static int getPersianDayOfMonth(Date date) {
		com.ibm.icu.util.Calendar calendar = com.ibm.icu.util.Calendar.getInstance(new ULocale(DEFAULT_LOCALE));
		calendar.setTime(date);
		return calendar.get(com.ibm.icu.util.Calendar.DAY_OF_MONTH);
	}

	public static Date getFirstMomentOfMonth(Date date) {
		com.ibm.icu.util.Calendar calendar = com.ibm.icu.util.Calendar.getInstance(new ULocale(DEFAULT_LOCALE));
		calendar.setTime(getFirstDayOfMonth(date));
		calendar.set(com.ibm.icu.util.Calendar.HOUR_OF_DAY, 0);
		calendar.set(com.ibm.icu.util.Calendar.MINUTE, 0);
		calendar.set(com.ibm.icu.util.Calendar.SECOND, 0);
		calendar.set(com.ibm.icu.util.Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	public static Date getLastDayOfMonth(Date date) {
		com.ibm.icu.util.Calendar calendar = com.ibm.icu.util.Calendar.getInstance(new ULocale(DEFAULT_LOCALE));
		calendar.setTime(date);
		calendar.set(com.ibm.icu.util.Calendar.DAY_OF_MONTH,
				calendar.getActualMaximum(com.ibm.icu.util.Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}

	public static Date getLastMomentOfMonth(Date date) {
		com.ibm.icu.util.Calendar calendar = com.ibm.icu.util.Calendar.getInstance(new ULocale(DEFAULT_LOCALE));
		calendar.setTime(getLastDayOfMonth(date));
		calendar.set(com.ibm.icu.util.Calendar.HOUR_OF_DAY,
				calendar.getActualMaximum(com.ibm.icu.util.Calendar.HOUR_OF_DAY));
		calendar.set(com.ibm.icu.util.Calendar.MINUTE, calendar.getActualMaximum(com.ibm.icu.util.Calendar.MINUTE));
		calendar.set(com.ibm.icu.util.Calendar.SECOND, calendar.getActualMaximum(com.ibm.icu.util.Calendar.SECOND));
		calendar.set(com.ibm.icu.util.Calendar.MILLISECOND,
				calendar.getActualMaximum(com.ibm.icu.util.Calendar.MILLISECOND));
		return calendar.getTime();
	}

	public static Date getLastMomentOfDay(Date date) {
		com.ibm.icu.util.Calendar calendar = com.ibm.icu.util.Calendar.getInstance(new ULocale(DEFAULT_LOCALE));
		calendar.setTime(date);
		calendar.set(com.ibm.icu.util.Calendar.HOUR_OF_DAY,
				calendar.getActualMaximum(com.ibm.icu.util.Calendar.HOUR_OF_DAY));
		calendar.set(com.ibm.icu.util.Calendar.MINUTE, calendar.getActualMaximum(com.ibm.icu.util.Calendar.MINUTE));
		calendar.set(com.ibm.icu.util.Calendar.SECOND, calendar.getActualMaximum(com.ibm.icu.util.Calendar.SECOND));
		calendar.set(com.ibm.icu.util.Calendar.MILLISECOND,
				calendar.getActualMaximum(com.ibm.icu.util.Calendar.MILLISECOND));

		return calendar.getTime();
	}

	public static Date getFirstMomentOfDay(Date date) {
		com.ibm.icu.util.Calendar calendar = com.ibm.icu.util.Calendar.getInstance(new ULocale(DEFAULT_LOCALE));
		calendar.setTime(date);
		calendar.set(com.ibm.icu.util.Calendar.HOUR_OF_DAY, 0);
		calendar.set(com.ibm.icu.util.Calendar.MINUTE, 0);
		calendar.set(com.ibm.icu.util.Calendar.SECOND, 0);
		calendar.set(com.ibm.icu.util.Calendar.MILLISECOND, 0);

		return calendar.getTime();
	}

	public static boolean isFirstDayOfMonth(Date date) {
		Date firstDayOfMonth = getFirstDayOfMonth(date);
		return date.compareTo(firstDayOfMonth) == 0;
	}

	public static boolean isLastDayOfMonth(Date date) {
		Date lastDayOfMonth = getLastDayOfMonth(date);
		return date.compareTo(lastDayOfMonth) == 0;
	}

	public static Date getFirstSecondOfDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	public static Date getLastSecondOfDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}

	public static boolean isToday(Date date) {
		Calendar today = Calendar.getInstance();
		Calendar specifiedDate = Calendar.getInstance();
		specifiedDate.setTime(date);

		return today.get(Calendar.DAY_OF_MONTH) == specifiedDate.get(Calendar.DAY_OF_MONTH)
				&& today.get(Calendar.MONTH) == specifiedDate.get(Calendar.MONTH)
				&& today.get(Calendar.YEAR) == specifiedDate.get(Calendar.YEAR);
	}

	public static boolean isBetweenTimes(int beginning, int ending, TimeUnit timeUnit) {
		if (!TimeUnit.HOUR.equals(timeUnit)) {
			throw new UnsupportedOperationException("only HOUR time unit is supported");
		}

		Calendar from = Calendar.getInstance();
		from.setTime(new Date());
		from.set(Calendar.HOUR_OF_DAY, beginning);
		from.set(Calendar.MINUTE, 0);
		from.set(Calendar.SECOND, 0);
		from.set(Calendar.MILLISECOND, 0);

		Calendar to = Calendar.getInstance();
		to.setTime(new Date());
		to.set(Calendar.HOUR_OF_DAY, ending);
		to.set(Calendar.MINUTE, 0);
		to.set(Calendar.SECOND, 0);
		to.set(Calendar.MILLISECOND, 0);

		Date date = new Date();
		return date.after(from.getTime()) && date.before(to.getTime());
	}

	public static boolean equalsAtDays(Date date1, Date date2) {
		if (date1 == date2) {
			return true;
		}
		if (date1 == null || date2 == null) {
			return false;
		}
		return getFirstMomentOfDay(date1).equals(getFirstMomentOfDay(date2));
	}

}
