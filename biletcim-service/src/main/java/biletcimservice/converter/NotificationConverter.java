package biletcimservice.converter;

import org.springframework.stereotype.Component;

import biletcimservice.model.Notification;
import biletcimservice.model.User;
import biletcimservice.requests.NotificationCreateRequest;

@Component
public class NotificationConverter {
	public Notification convert(NotificationCreateRequest createRequest) {
		Notification notification = new Notification();
		notification.setSendingDate(createRequest.getDateTime());
		notification.setQueueName(createRequest.getQueueName());
		notification.setUser(new User(createRequest.getUser_id()));
		
		return notification;
	}
}
