package modules.booking.controller;

import modules.booking.dto.BookingRequest;
import modules.booking.service.BookingService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService service;

    public BookingController(BookingService service) {
        this.service = service;
    }

    @PostMapping
    public String createBooking(@RequestBody BookingRequest request) {
        return service.createBooking(request);
    }
}