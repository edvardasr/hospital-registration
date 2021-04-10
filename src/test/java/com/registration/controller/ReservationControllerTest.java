package com.registration.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import com.registration.entities.Reservation;
import com.registration.mongo.Repository;
import com.registration.utils.WeekOfYearValidator;

@ExtendWith(MockitoExtension.class)
public class ReservationControllerTest {

	@Mock
	private Repository repository;

	private WeekOfYearValidator weekOfYearValidator;

	private ReservationController reservationController;

	@BeforeEach
	void init() {
		reservationController = new ReservationController();

		reservationController.setRepository(repository);
		weekOfYearValidator = new WeekOfYearValidator();
		reservationController.setValidator(weekOfYearValidator);
	}

	@Test
	void test_showForm_succes() {

		// act
		ModelAndView modelAndView = reservationController.showForm();

		// verify
		assertNotEquals(null, modelAndView);
		assertEquals(modelAndView.getViewName(), "registrationHome");
		assertEquals(modelAndView.getModelMap().containsKey("reservation"), true);
	}

	@Test
	void test_getReservationList_succes() {

		// act
		ModelAndView modelAndView = reservationController.getReservationList(anyString());

		// verify
		assertNotEquals(null, modelAndView);
		assertEquals(modelAndView.getViewName(), "reservationList");
		assertEquals(modelAndView.getModelMap().containsKey("list"), true);
	}

	@Test
	void test_addReservation_succes() {

		// prepare
		ModelMap modelMap = new ModelMap();
		Reservation reservation = new Reservation();

		reservation.setId("id");
		reservation.setName("name");
		reservation.setSurname("surname");
		reservation.setDate("date");

		// act
		String result = reservationController.addReservation(reservation, null, modelMap);

		// verify
		assertEquals("reservationAdd", result);
		assertEquals(modelMap.get("id"), "id");
		assertEquals(modelMap.get("name"), "name");
		assertEquals(modelMap.get("date"), "date");
	}

	@Test
	void test_cancel_succes() {

		// prepare
		ModelMap modelMap = new ModelMap();
		Reservation reservation = new Reservation();

		// act
		String result = reservationController.cancel(reservation, null, modelMap);

		// verify
		assertEquals("registrationHome", result);
		assertEquals(modelMap.get("message"), "You clicked cancel, please re-enter employee details:");
	}

	@Test
	void test_submit_error() {

		// prepare
		ModelMap modelMap = new ModelMap();
		Reservation reservation = new Reservation();

		reservation.setId("id");
		reservation.setName("name");
		reservation.setSurname("surname");
		reservation.setDate("04/10/2021");

		Reservation reservation1 = new Reservation();

		reservation1.setId("id");
		reservation1.setName("name");
		reservation1.setSurname("surname");
		reservation1.setDate("04/09/2021");

		List<Reservation> reservationList = new ArrayList<Reservation>();
		reservationList.add(reservation1);

		when(repository.findAllById(reservation.getId())).thenReturn(reservationList);

		// act
		String result = reservationController.submit(reservation, null, modelMap);

		// verify
		assertEquals("error", result);
	}

	@Test
	void test_submit_succes() {

		// prepare
		ModelMap modelMap = new ModelMap();
		Reservation reservation = new Reservation();

		reservation.setId("id");
		reservation.setName("name");
		reservation.setSurname("surname");
		reservation.setDate("04/12/2021");

		Reservation reservation1 = new Reservation();

		reservation1.setId("id");
		reservation1.setName("name");
		reservation1.setSurname("surname");
		reservation1.setDate("04/09/2021");

		List<Reservation> reservationList = new ArrayList<Reservation>();
		reservationList.add(reservation1);

		when(repository.findAllById(reservation.getId())).thenReturn(reservationList);

		// act
		String result = reservationController.submit(reservation, null, modelMap);

		// verify
		verify(repository).insertOne(reservation);
		assertEquals("registrationHome", result);
	}
}
