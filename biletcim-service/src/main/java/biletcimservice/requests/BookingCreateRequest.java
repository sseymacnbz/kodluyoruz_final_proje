package biletcimservice.requests;

import lombok.Data;

@Data

public class BookingCreateRequest {
	private int user_id;
	private int trip_id;
}
