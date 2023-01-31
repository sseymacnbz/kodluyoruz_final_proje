package biletcimservice.converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import biletcimservice.model.Trip;
import biletcimservice.requests.TripCreateRequest;

@Component
public class TripConverter {
	public Trip convert(TripCreateRequest createRequest) {
		Trip trip = new Trip();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		
		trip.setDateTime(LocalDateTime.parse(createRequest.getDateTime(), formatter));
		trip.setFrom(createRequest.getFrom());
		trip.setTo(createRequest.getTo());
		trip.setType(createRequest.getType());
		trip.setPrice(createRequest.getPrice());
		
		return trip;
	}
}
