package biletcimservice.converter;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import biletcimservice.model.Booking;import biletcimservice.model.Trip;
import biletcimservice.model.User;
import biletcimservice.requests.BookingCreateRequest;

@Component
public class BookingConverter {
	public Booking convert(BookingCreateRequest createRequest) {
		Booking booking = new Booking();
		booking.setBookingDate(LocalDateTime.now());
		booking.setTrip(new Trip(createRequest.getTrip_id()));
		booking.setUser(new User(createRequest.getUser_id()));
		return booking;
	}
}
