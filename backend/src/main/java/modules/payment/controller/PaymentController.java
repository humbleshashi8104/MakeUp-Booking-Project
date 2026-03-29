package modules.payment.controller;

import com.razorpay.RazorpayException;
import modules.payment.service.PaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @PostMapping("/create-order")
    public String createOrder(@RequestParam Long bookingId, @RequestParam Double amount) throws RazorpayException {
        return service.createOrder(bookingId, amount);
    }

    @PostMapping("/verify")
    public String verifyPayment(@RequestParam String orderId, @RequestParam String paymentId) {
        service.verifyPayment(orderId, paymentId);
        return "Payment verified & booking confirmed";
    }
}
