package common.scheduler;

import modules.booking.entity.Booking;
import modules.booking.repository.BookingRepository;
import modules.slot.entity.TimeSlot;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;import java.util.List;

@Component
public class BookingExpiryScheduler {
    private final BookingRepository bookingRepository;

    public BookingExpiryScheduler(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Scheduled(fixedRate = 60000) // every 1 minute
    @Transactional
    public void expireBookings() {

        List<Booking> bookings =
                bookingRepository.findByStatusAndExpiresAtBefore("PENDING", LocalDateTime.now());

        for (Booking booking : bookings) {
            booking.setStatus("CANCELLED");

            TimeSlot slot = booking.getSlot();
            if (slot != null) {
                slot.setStatus("AVAILABLE");
            }
        }

        bookingRepository.saveAll(bookings);
    }
}