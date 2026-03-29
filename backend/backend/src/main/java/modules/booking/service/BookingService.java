package modules.booking.service;

import modules.booking.dto.BookingRequest;
import modules.booking.entity.Booking;
import modules.booking.repository.BookingRepository;
import modules.servicecatalog.entity.ServiceEntity;
import modules.servicecatalog.repository.ServiceRepository;
import modules.slot.entity.TimeSlot;
import modules.slot.repository.TimeSlotRepository;
import modules.user.entity.User;
import modules.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final ServiceRepository serviceRepository;
    private final TimeSlotRepository slotRepository;

    public BookingService(BookingRepository bookingRepository,
                          UserRepository userRepository,
                          ServiceRepository serviceRepository,
                          TimeSlotRepository slotRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.serviceRepository = serviceRepository;
        this.slotRepository = slotRepository;
    }

    @Transactional
    public String createBooking(BookingRequest request) {

        TimeSlot slot = slotRepository.findById(request.getSlotId())
                .orElseThrow(() -> new RuntimeException("Slot not found"));

        if (!slot.getStatus().equals("AVAILABLE")) {
            throw new RuntimeException("Slot already booked");
        }

        slot.setStatus("BOOKED");

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        ServiceEntity service = serviceRepository.findById(request.getServiceId())
                .orElseThrow(() -> new RuntimeException("Service not found"));

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setService(service);
        booking.setSlot(slot);
        booking.setStatus("PENDING");
        booking.setCreatedAt(LocalDateTime.now());
        booking.setExpiresAt(LocalDateTime.now().plusMinutes(10));

        bookingRepository.save(booking);

        return "Booking successful";
    }
}