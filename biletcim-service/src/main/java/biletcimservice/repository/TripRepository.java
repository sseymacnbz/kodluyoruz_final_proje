package biletcimservice.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import biletcimservice.model.Trip;
import biletcimservice.model.enums.TripType;

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer>{

	List<Trip> findByType(TripType type);

	Optional<List<Trip>> findByFrom_(String from_);
	
	Optional<List<Trip>> findByTo_(String to_);

	List<Trip> findByDate(LocalDateTime date);

}
