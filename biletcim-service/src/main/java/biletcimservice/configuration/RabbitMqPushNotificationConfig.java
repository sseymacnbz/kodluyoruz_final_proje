package biletcimservice.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data

@Configuration
public class RabbitMqPushNotificationConfig implements QueueConfiguration{
	
	private String queueName = "biletcim.notification.push";
	
	private String exchange = "biletcim.notification.push";
	
	@Bean
	public Queue pushQueue() {
		return new Queue(queueName, false);
	}

	@Bean
	public DirectExchange pushExchange() {
		return new DirectExchange(exchange);
	}

	@Bean
	public Binding binding(Queue pushQueue, DirectExchange pushExchange) {
		return BindingBuilder.bind(pushQueue).to(pushExchange).with("");
	}
	
	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
}
