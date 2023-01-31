package biletcimservice.controller;

import java.util.List;

import org.springframework.amqp.AmqpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import biletcimservice.model.Booking;
import biletcimservice.requests.BookingCreateRequest;
import biletcimservice.service.BookingService;

@RestController
@RequestMapping(value="/bookings")
public class BookingController {
	@Autowired
	private BookingService bookingService;
	
	@PostMapping
	public ResponseEntity<String> create(@RequestBody BookingCreateRequest createRequest) throws AmqpException, Exception{
		return ResponseEntity.ok(bookingService.create(createRequest));
	}
	
	@PostMapping(value="/findAll")
	public List<Booking> findAll(@RequestBody int admin_id){
		return bookingService.findAll(admin_id);
	}
	
	@PostMapping(value="/totalPrice")
	public int totalPrice(@RequestBody int admin_id){
		return bookingService.totalPrice(admin_id);
	}
}
