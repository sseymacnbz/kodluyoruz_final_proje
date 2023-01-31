package biletcimservice.requests;

import biletcimservice.model.enums.TripType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class TripCreateRequest {
	private int user_id;
	private String dateTime;
	private String from;
	private String to;
	private TripType type;
	private double price;
	
}
