package biletcimservice.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import biletcimservice.client.Payment;
import biletcimservice.client.PaymentServiceClient;
import biletcimservice.configuration.QueueFactory;
import biletcimservice.converter.BookingConverter;
import biletcimservice.converter.NotificationConverter;
import biletcimservice.exception.UserNotFoundException;
import biletcimservice.model.Booking;
import biletcimservice.model.User;
import biletcimservice.model.enums.UserRole;
import biletcimservice.model.enums.UserType;
import biletcimservice.repository.BookingRepository;
import biletcimservice.repository.NotificationRepository;
import biletcimservice.repository.UserRepository;
import biletcimservice.requests.BookingCreateRequest;
import biletcimservice.requests.NotificationCreateRequest;

@Service
public class BookingService {
	private List<Booking> bookingList = new ArrayList<>();
	private static int SUM_OF_BOOKING_PRICE = 0;
	private static int INDIVIDUAL_MAX_TICKET=5;
	private static int CORPORATE_MAX_TICKET=20;
	private static String MESSAGE;
	@Autowired
	private BookingRepository bookingRepository;
	@Autowired
	private BookingConverter converter;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RabbitTemplate rabbitTemplate;
	@Autowired
	private QueueFactory queueFactory;
	@Autowired
	private NotificationRepository notificationRepository;
	@Autowired
	private NotificationConverter notificationConverter;
	@Autowired
	private PaymentServiceClient paymentClient;
	//create işlemi esnasında kullanıcının tipine göre payment,notification gibi işlemleri yapar
	public String paymentForCreate(User user, int max_ticket, BookingCreateRequest createRequest) throws Exception{
		bookingList.clear();
		
		for(Booking booking: user.getBookings()) {
			if (booking.getTrip().getId() == createRequest.getTrip_id()) bookingList.add(booking);
		}
		
		if(bookingList.size()<=max_ticket) {
			paymentClient.paymentRequest(new Payment(createRequest.getUser_id(), "ODEME BASARILI!"));
			bookingRepository.save(converter.convert(createRequest));
			try {
				rabbitTemplate.convertAndSend(queueFactory.createConfig("sms"),user);
			} catch (AmqpException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			notificationRepository
			.save(notificationConverter.convert(new NotificationCreateRequest(user.getId(), LocalDateTime.now(), queueFactory.createConfig("sms"))));
			MESSAGE="Isleminiz BASARILI";
		}
		
		else {
			MESSAGE="Ayni Sefer Icin Maksimum "+max_ticket +" Bilet Alabilirsiniz";
		}
		return MESSAGE;
	}
	
	public String create(BookingCreateRequest createRequest) throws AmqpException, Exception  {
			//Bilet alma işlemini yapacak olan user
			User user = userRepository.findById(createRequest.getUser_id())
					.orElseThrow(()-> new UserNotFoundException("KULLANICI BULUNAMADI"));
			
			if(userRepository.getReferenceById(createRequest.getUser_id())!=null)
			
			if(UserType.INDIVIDUAL.equals(user.getType())) {
				MESSAGE = paymentForCreate(user,INDIVIDUAL_MAX_TICKET,createRequest);
			}
			else {
				MESSAGE = paymentForCreate(user,CORPORATE_MAX_TICKET,createRequest);
			}
			return MESSAGE;
	}
	
	
	public List<Booking> findAll(int admin_id) {
		User user = userRepository.findById(admin_id)
				.orElseThrow(()-> new UserNotFoundException("KULLANICI BULUNAMADI"));
		bookingList.clear();
		if(user.getRole() == UserRole.ADMIN) {
			bookingList = bookingRepository.findAll();
		}
		return bookingList;
	}
	
	
	public int totalPrice(int admin_id) {
		bookingList.clear();
		User user = userRepository.findById(admin_id)
				.orElseThrow(()-> new UserNotFoundException("KULLANICI BULUNAMADI"));
		if(user.getRole() == UserRole.ADMIN) {
			bookingList = bookingRepository.findAll();
		}
		
		for (Booking booking : bookingList){
			SUM_OF_BOOKING_PRICE += booking.getTrip().getPrice();
		}
		
		return SUM_OF_BOOKING_PRICE;
	}

}
