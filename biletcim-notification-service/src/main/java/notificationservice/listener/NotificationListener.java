package notificationservice.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import notificationservice.model.User;

@Component
public class NotificationListener {
	
	@RabbitListener(queues = "biletcim.notification.mail")
	public void mailListener(User user) {
		System.out.println("Kayit islemi basarili. "+user.getMail()+" maili icin bilgilendirme mail'i gönderilecektir");
	}
	
	@RabbitListener(queues = "biletcim.notification.sms")
	public void smsListener(User user) {
		System.out.println("Bilet basariyla alindi. Bilet bilgileri "+user.getPhone().substring(5)+" ile biten numaraya sms yoluyla gonderilecektir.");
	}
	
	@RabbitListener(queues = "biletcim.notification.push")
	public void pushListener(User user) {
		System.out.println("Notification gonderildi");
	}
}
