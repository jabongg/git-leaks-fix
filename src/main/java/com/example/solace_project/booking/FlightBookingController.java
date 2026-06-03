package com.example.solace_project.booking;

import java.util.Collection;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/bookings")
public class FlightBookingController {
	private final FlightBookingService bookingService;

	public FlightBookingController(FlightBookingService bookingService) {
		this.bookingService = bookingService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.ACCEPTED)
	public FlightBooking create(@RequestBody FlightBookingRequest request) {
		log.info("<=========inside create() method=======>");
		return bookingService.create(request);
	}

	@GetMapping
	public Collection<FlightBooking> findAll() {
		log.info("<=========inside findAll() method=======>");

		return bookingService.findAll();
	}

	@ExceptionHandler(BookingNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Map<String, String> handleNotFound(BookingNotFoundException exception) {
		log.info("<=========inside handleNotFound() method=======>");
		return Map.of("error", exception.getMessage());
	}

	@ExceptionHandler(FeatureDisabledException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Map<String, String> handleFeatureDisabled(FeatureDisabledException exception) {
		log.info("<=========inside handleFeatureDisabled() method=======>");
		return Map.of("error", exception.getMessage());
	}
}
