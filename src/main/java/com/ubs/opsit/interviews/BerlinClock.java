package com.ubs.opsit.interviews;

import java.util.Arrays;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class BerlinClock implements TimeConverter {
	
	/**
	 * The default local time format is: HH:mm:ss, for example: 12:03:30
	 */
	protected static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";

	/**
	 * Gets Berlin Clock status in second.
	 * @param number seconds in a local time
	 * @return second's representation in a Berlin Clock
	 */
	protected String getSeconds(int number) {
		if (number % 2 == 0)
			return "Y";
		else
			return "O";
	}

	/**
	 * Gets top hours of a Berlin Clock from hours in a local time
	 * @param number the hours in a local time
	 * @return The top hours in a Berlin Clock
	 */
	protected String getTopHours(int number) {
		return getOnOff(4, getTopNumberOfOnSigns(number));
	}

	/**
	 * Gets bottom hours of a Berlin Clock from hours in a local time
	 * @param number the hours in a local time
	 * @return The bottom hours of a Berlin Clock
	 */
	protected String getBottomHours(int number) {
		return getOnOff(4, number % 5);
	}

	/**
	 * Gets top minutes of a Berlin Clock from minutes in a local time
	 * @param number the minutes in a local time
	 * @return The top minutes of a Berlin Clock
	 */
	protected String getTopMinutes(int number) {
		return getOnOff(11, getTopNumberOfOnSigns(number), "Y").replaceAll("YYY", "YYR");
	}

	/**
	 * Gets bottom minutes of a Berlin Clock from minutes in a local time
	 * @param number the minutes in a local time
	 * @return The bottom minutes of a Berlin Clock
	 */
	protected String getBottomMinutes(int number) {
		return getOnOff(4, number % 5, "Y");
	}

	// Default value for onSign would be useful
	private String getOnOff(int lamps, int onSigns) {
		return getOnOff(lamps, onSigns, "R");
	}

	private String getOnOff(int lamps, int onSigns, String onSign) {
		String out = "";
		// String multiplication would be useful
		for (int i = 0; i < onSigns; i++) {
			out += onSign;
		}
		for (int i = 0; i < (lamps - onSigns); i++) {
			out += "O";
		}
		return out;
	}

	private int getTopNumberOfOnSigns(int number) {
		return (number - (number % 5)) / 5;
	}


	protected String[] convertToBerlinTime(String time) {
		int[] parts = Arrays.asList(time.split(":")).stream().mapToInt(Integer::parseInt).toArray();
		return new String[] { getSeconds(parts[2]), getTopHours(parts[0]), getBottomHours(parts[0]),
				getTopMinutes(parts[1]), getBottomMinutes(parts[1]) };
	}
	
	/**
	 * Returns a Berlin Clock status from a local time (in format {@link #DEFAULT_TIME_FORMAT})   
	 * <p>
	 * Given a local time in format {@link #DEFAULT_TIME_FORMAT}, this method returns a time representation in Berlin Clock. 
	 * If the time format is wrong, then a DateTimeParseException will be thrown.
	 *
	 * @param  aTime  a local time in format: {@link #DEFAULT_TIME_FORMAT}
	 * @return      the Berlin Clock status corresponding to the given local time.
	 * @exception DateTimeParseException if the time format is not: {@link #DEFAULT_TIME_FORMAT}
	 * @see  DateTimeParseException
	 */
	@Override
	public String convertTime(String aTime)  {
		LocalTime.parse(aTime, DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT));
		return String.join("\n", convertToBerlinTime(aTime));
	}

}
