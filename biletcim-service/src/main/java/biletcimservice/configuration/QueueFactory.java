package biletcimservice.configuration;

import org.springframework.stereotype.Component;

@Component
public class QueueFactory {
	
	public String createConfig(String type) throws Exception{
		if(type.equalsIgnoreCase("mail")) {
			return new RabbitMqMailConfig().getQueueName();
		}
		
		else if (type.equalsIgnoreCase("sms")) {
			return new RabbitMqSmsConfig().getQueueName();
		}
		
		else if (type.equalsIgnoreCase("push")) {
			return new RabbitMqPushNotificationConfig().getQueueName();
		}
		else {
			throw new Exception("Yanlis girdi yapildi");
		}
	}
}
