package biletcimservice.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import biletcimservice.converter.TripConverter;
import biletcimservice.exception.LocationNotFoundException;
import biletcimservice.exception.UserNotFoundException;
import biletcimservice.model.Booking;
import biletcimservice.model.Trip;
import biletcimservice.model.User;
import biletcimservice.model.enums.TripType;
import biletcimservice.model.enums.UserRole;
import biletcimservice.repository.BookingRepository;
import biletcimservice.repository.TripRepository;
import biletcimservice.repository.UserRepository;
import biletcimservice.requests.TripCreateRequest;
import biletcimservice.requests.TripDeleteRequest;

@Service
public class TripService {
	private String message;
	@Autowired
	private TripRepository tripRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TripConverter converter;
	
	
	public String create(TripCreateRequest createRequest) {
		try {
			User user = userRepository.findById(createRequest.getUser_id())
					.orElseThrow(()-> new UserNotFoundException("KULLANICI BULUNAMADI"));
			if(user.getRole() == UserRole.ADMIN) {
				tripRepository.save(converter.convert(createRequest));
				message="Sefer Kaydedildi";
			}
		} catch (Exception e) {
			message="Admin rolunde degilsiniz";
		}
		
		return message;
	}

	public String delete(TripDeleteRequest deleteRequest) {
		tripRepository.deleteById(deleteRequest.getId());
		return "Islem Gerçekleştirildi";
	}

	public List<Trip> findByType(TripType type) {
		return tripRepository.findByType(type);
	}

	public List<Trip> findByFrom_(String from_) {
		return tripRepository.findByFrom_(from_)
				.orElseThrow(()-> new LocationNotFoundException("Seçtiğiniz konum için bilgi bulunamadı"));
	}
	
	public List<Trip> findByTo_(String To_) {
		return tripRepository.findByTo_(To_)
				.orElseThrow(()-> new LocationNotFoundException("Seçtiğiniz konum için bilgi bulunamadı"));
	}
	
	public List<Trip> findByDate(LocalDateTime date) {
		return tripRepository.findByDate(date);
	}
}
