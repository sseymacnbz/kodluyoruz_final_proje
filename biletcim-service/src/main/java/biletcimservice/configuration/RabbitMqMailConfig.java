package biletcimservice.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data

@Configuration
public class RabbitMqMailConfig implements QueueConfiguration{
	
	
	private String queueName = "biletcim.notification.mail";
	private String exchange = "biletcim.notification.mail";
	
	@Bean
	public Queue mailQueue() {
		return new Queue(queueName, false);
	}

	@Bean
	public DirectExchange mailExchange() {
		return new DirectExchange(exchange);
	}

	@Bean
	public Binding binding(Queue mailQueue, DirectExchange mailExchange) {
		return BindingBuilder.bind(mailQueue).to(mailExchange).with("");
	}
	
	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
}
