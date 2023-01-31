package biletcimservice.requests;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class NotificationCreateRequest {
	private int user_id;
	private LocalDateTime dateTime;
	private String queueName;

}
