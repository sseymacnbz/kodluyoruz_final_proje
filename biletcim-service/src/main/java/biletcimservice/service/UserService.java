package biletcimservice.service;

import java.time.LocalDateTime;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import biletcimservice.configuration.QueueFactory;
import biletcimservice.converter.NotificationConverter;
import biletcimservice.converter.UserConverter;
import biletcimservice.exception.UserNotFoundException;
import biletcimservice.repository.NotificationRepository;
import biletcimservice.repository.UserRepository;
import biletcimservice.requests.NotificationCreateRequest;
import biletcimservice.requests.UserAssignAdminRequest;
import biletcimservice.requests.UserCreateRequest;
import biletcimservice.requests.UserLoginRequest;
import biletcimservice.model.User;
import biletcimservice.model.enums.UserRole;
import biletcimservice.util_.PasswordUtil;


@Service
public class UserService {
	private static final String KAYIT_BASARILI = "Kayit Islemi Basarili";
	private static final String KAYIT_BASARISIZ = "Kayit Islemi Basarsiz";
	
	private static final String EMAIL_VEYA_SIFRE_HATALI = "Email veya sifre hatali";
	private static final String LOGIN_BASARILI = "Login Basarili";
	
	
	private String message;
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserConverter converter;
	@Autowired
	private NotificationConverter notificationConverter;
	@Autowired
	private RabbitTemplate rabbitTemplate;
	@Autowired
	private QueueFactory queueFactory;
	@Autowired
	private NotificationRepository notificationRepository;
	
	public String create(UserCreateRequest createRequest) {
		
		try {
			User user = userRepository.save(converter.convert(createRequest));
			System.out.println(user.getId());
			message = KAYIT_BASARILI;
			rabbitTemplate.convertAndSend(queueFactory.createConfig("mail"),user);
			notificationRepository
			.save(notificationConverter.convert(new NotificationCreateRequest(user.getId(), LocalDateTime.now(), queueFactory.createConfig("mail"))));
		} catch (Exception e) {
			message = KAYIT_BASARISIZ;
		}
		return message;
	}
	
	public String assignAdmin(UserAssignAdminRequest adminAssignRequest) {
		User user = userRepository.findByMail(adminAssignRequest.getMail())
				.orElseThrow(()-> new UserNotFoundException("KULLANICI BULUNAMADI"));
		user.setRole(UserRole.ADMIN);
		userRepository.save(user);
		return "Admin Atandi";
	}

	public String login(UserLoginRequest loginRequest){
		User foundedUser = userRepository.findByMail(loginRequest.getMail())
				.orElseThrow(()-> new UserNotFoundException("KULLANICI BULUNAMADI"));
		
		String passwordHash = PasswordUtil.preparePasswordHash(loginRequest.getPassword(), loginRequest.getMail());
		
		boolean isValid = PasswordUtil.validatePassword(passwordHash, foundedUser.getPassword());
		
		return isValid ? LOGIN_BASARILI:EMAIL_VEYA_SIFRE_HATALI;
	}

}
