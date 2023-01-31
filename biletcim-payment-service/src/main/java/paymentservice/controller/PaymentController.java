package paymentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import paymentservice.model.Payment;
import paymentservice.service.PaymentService;

@RestController
@RequestMapping(value="/payment")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	public void paymentRequest(@RequestBody Payment payment){
		paymentService.paymentRequest(payment);
	}
}
