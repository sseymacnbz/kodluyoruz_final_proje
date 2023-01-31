package biletcimservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value="biletcim-payment-service", url="http://localhost:8081")
public interface PaymentServiceClient {
	@PostMapping(value="/payment")
	Payment paymentRequest(@RequestBody Payment payment);
}
