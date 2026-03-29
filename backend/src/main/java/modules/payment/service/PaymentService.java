package modules.payment.service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import modules.booking.entity.Booking;
import modules.booking.repository.BookingRepository;
import modules.payment.entity.Payment;
import modules.payment.repository.PaymentRepository;
import modules.slot.entity.TimeSlot;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentService {

    @Value("${razorpay.key}")
    private String key;

    @Value("${razorpay.secret}")
    private String secret;

    private final PaymentRepository repository;
    private final BookingRepository bookingRepository;

    public PaymentService(PaymentRepository repository, BookingRepository bookingRepository) {
        this.repository = repository;
        this.bookingRepository = bookingRepository;
    }

    public String createOrder(Long bookingId, Double amount) throws RazorpayException {

        RazorpayClient client = new RazorpayClient(key, secret);

        JSONObject options = new JSONObject();
        options.put("amount", amount * 100);
        options.put("currency", "INR");

        Order order = client.orders.create(options);

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow();

        Payment payment = new Payment();
        payment.setBooking(booking);
        payment.setRazorpayOrderId(order.get("id"));
        payment.setAmount(amount);
        payment.setStatus("CREATED");

        repository.save(payment);

        return order.toString();
    }

    @Transactional
    public void verifyPayment(String orderId, String paymentId) {

        Payment payment = repository.findByRazorpayOrderId(orderId)
                .orElseThrow();

        payment.setRazorpayPaymentId(paymentId);
        payment.setStatus("SUCCESS");

        Booking booking = payment.getBooking();
        booking.setStatus("CONFIRMED");

        TimeSlot slot = booking.getSlot();
        slot.setStatus("BOOKED");

        repository.save(payment);
    }

    @Transactional
    public void handlePaymentFailure(String orderId) {

        Payment payment = repository.findByRazorpayOrderId(orderId)
                .orElseThrow();

        payment.setStatus("FAILED");

        Booking booking = payment.getBooking();
        booking.setStatus("CANCELLED");

        TimeSlot slot = booking.getSlot();
        slot.setStatus("AVAILABLE");

        repository.save(payment);
    }
}
