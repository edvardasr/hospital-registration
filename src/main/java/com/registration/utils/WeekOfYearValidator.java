package com.registration.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.registration.controller.ReservationController;
import com.registration.entities.Reservation;

@Component
public class WeekOfYearValidator {

	private static final Logger logger = LoggerFactory.getLogger(ReservationController.class);

	public WeekOfYearValidator() {

	}

	public int getWeekOfTheYear(Reservation reservation) {

		Date date = null;
		try {
			date = new SimpleDateFormat("MM/dd/yyyy").parse(reservation.getDate());
		} catch (ParseException e) {
			logger.debug("Incorrect date format", e);
		}
		Calendar targetCalendar = Calendar.getInstance();
		targetCalendar.setTime(date);
		int targetWeek = targetCalendar.get(Calendar.WEEK_OF_YEAR);
		return targetWeek;
	}
}
