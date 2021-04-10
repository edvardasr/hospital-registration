package com.registration.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.registration.entities.Reservation;
import com.registration.mongo.Repository;
import com.registration.utils.WeekOfYearValidator;

@Controller
public class ReservationController {

	private static final Logger logger = LoggerFactory.getLogger(ReservationController.class);

	@Autowired
	private Repository repository;

	@Autowired
	private WeekOfYearValidator validator;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView showForm() {
		return new ModelAndView("registrationHome", "reservation", new Reservation());
	}

	@RequestMapping(value = "/reservationList", method = RequestMethod.GET)
	public ModelAndView getReservationList(@RequestParam final String id) {

		List<Reservation> list = repository.findAllById(id);
		

		ModelAndView model = new ModelAndView("reservationList");
		model.addObject("list", list);

		return model;
	}

	@RequestMapping(value = "/addReservation", method = RequestMethod.POST)
	public String addReservation(@ModelAttribute("reservation") Reservation reservation, BindingResult result,
			ModelMap model) {
	
		model.addAttribute("id", reservation.getId());
		model.addAttribute("name", reservation.getName());
		model.addAttribute("surname", reservation.getSurname());
		model.addAttribute("date", reservation.getDate());

		return "reservationAdd";
	}

	@RequestMapping(value = "/addReservation", method = RequestMethod.POST, params = "cancel")
	public String cancel(@ModelAttribute("reservation") final Reservation reservation, final BindingResult result,
			final ModelMap model) {
		model.addAttribute("message", "You clicked cancel, please re-enter employee details:");
		
		return "registrationHome";
	}

	@RequestMapping(value = "/addReservation", method = RequestMethod.POST, params = "submit")
	public String submit(@ModelAttribute("reservation") final Reservation reservation, final BindingResult result,
			final ModelMap model) {

		logger.debug("Entering submit");

		int targetWeek = validator.getWeekOfTheYear(reservation);

		List<Reservation> reservations = repository.findAllById(reservation.getId());

		for (Reservation rezervation : reservations) {

			if (targetWeek == validator.getWeekOfTheYear(rezervation)) {
				logger.debug("There are another registrations within the week '{}'", targetWeek);
				return "error";
			}
		}

		repository.insertOne(reservation);

		logger.debug("Leaving submit");

		return "registrationHome";
	}

	public void setRepository(Repository repository) {
		this.repository = repository;
	}

	public void setValidator(WeekOfYearValidator validator) {
		this.validator = validator;
	}
}
