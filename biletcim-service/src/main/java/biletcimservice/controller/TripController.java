package biletcimservice.controller;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import biletcimservice.model.Trip;
import biletcimservice.model.enums.TripType;
import biletcimservice.requests.TripCreateRequest;
import biletcimservice.requests.TripDeleteRequest;
import biletcimservice.service.TripService;

@RestController
@RequestMapping(value="/trips")
public class TripController {
	
	@Autowired
	private TripService tripService; 
	
	// Adminse trip oluştur
	@PostMapping
	public ResponseEntity<String> create(@RequestBody TripCreateRequest createRequest){
		return ResponseEntity.ok(tripService.create(createRequest));
	}
	
	// Adminse trip sil
	@PostMapping(value="/deleteTrip")
	public ResponseEntity<String> delete(@RequestBody TripDeleteRequest deleteRequest){
		return ResponseEntity.ok(tripService.delete(deleteRequest));
	}
	
	@PostMapping(value="/findByType")
	public List<Trip> findByType(TripType type){
		return tripService.findByType(type);
	}
	
	@PostMapping(value="/findByFrom")
	public List<Trip> findByFrom_(String from_){
		return tripService.findByFrom_(from_);
	}
	
	@PostMapping(value="/findByTo")
	public List<Trip> findByTo_(String to_){
		return tripService.findByTo_(to_);
	}
	
	@PostMapping(value="/findByDate")
	public List<Trip> findByDate(LocalDateTime date){
		return tripService.findByDate(date);
	}
	
	
}
