package paymentservice.repository;

import org.springframework.stereotype.Repository;

import paymentservice.model.Payment;

@Repository
public class PaymentRepository {
	public void paymentRequest(Payment payment){
		System.out.println(payment.getMessage());
	}
}
