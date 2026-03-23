package modules.slot.repository;

import jakarta.persistence.LockModeType;
import modules.slot.entity.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {

    @Override
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<TimeSlot> findById(Long id);

    List<TimeSlot> findByDate(LocalDate date);
}
