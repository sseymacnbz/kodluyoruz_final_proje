package biletcimservice.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import biletcimservice.model.enums.TripType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name="Trip")
public class Trip {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	
	@Column(name="date")
	private LocalDateTime dateTime;
	
	@Column(name="from_")
	private String from;
	
	@Column(name="to_")
	private String to;
	
	@Column(name="type")
	@Enumerated(EnumType.STRING)
	private TripType type;
	
	@Column(name="price")
	private double price;
	
	@OneToMany(mappedBy="trip")
	@JsonBackReference
	private List<Booking> bookings;
	
	public Trip(int id) {
		this.id=id;
	}
	
}
