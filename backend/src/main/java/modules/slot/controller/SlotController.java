package modules.slot.controller;

import modules.slot.entity.TimeSlot;
import modules.slot.service.SlotService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/slots")
public class SlotController {

    private final SlotService service;

    public SlotController(SlotService service) {
        this.service = service;
    }

    @PostMapping("/generate")
    public String generateSlots(@RequestParam String date) {

        service.generateSlots(LocalDate.parse(date));
        return "Slots generated successfully";
    }

    @GetMapping
    public List<TimeSlot> getSlots(@RequestParam String date) {
        return service.getSlotsByDate(LocalDate.parse(date));
    }
}