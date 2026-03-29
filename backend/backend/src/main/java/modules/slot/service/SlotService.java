package modules.slot.service;

import modules.slot.entity.TimeSlot;
import modules.slot.repository.TimeSlotRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class SlotService {

    private final TimeSlotRepository repository;

    public SlotService(TimeSlotRepository repository) {
        this.repository = repository;
    }

    public void generateSlots(LocalDate date) {

        for (int hour = 10; hour < 18; hour++) {

            TimeSlot slot = new TimeSlot();
            slot.setDate(date);
            slot.setStartTime(LocalTime.of(hour, 0));
            slot.setEndTime(LocalTime.of(hour + 1, 0));
            slot.setStatus("AVAILABLE");

            repository.save(slot);
        }
    }

    public List<TimeSlot> getSlotsByDate(LocalDate date) {
        return repository.findByDate(date);
    }
}