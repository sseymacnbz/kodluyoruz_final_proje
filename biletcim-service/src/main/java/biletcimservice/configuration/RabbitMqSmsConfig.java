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
public class RabbitMqSmsConfig implements QueueConfiguration{
	
	private String queueName = "biletcim.notification.sms";
	
	private String exchange = "biletcim.notification.sms";
	
	@Bean
	public Queue smsQueue() {
		return new Queue(queueName, false);
	}

	@Bean
	public DirectExchange smsExchange() {
		return new DirectExchange(exchange);
	}

	@Bean
	public Binding binding(Queue smsQueue, DirectExchange smsExchange) {
		return BindingBuilder.bind(smsQueue).to(smsExchange).with("");
	}
	
	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
}
