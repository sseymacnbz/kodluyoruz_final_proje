package paymentservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import paymentservice.model.Payment;
import paymentservice.repository.PaymentRepository;

@Service
public class PaymentService {
	@Autowired
	private PaymentRepository repository;
	
	public void paymentRequest(Payment payment) {
		repository.paymentRequest(payment);
	}
}
