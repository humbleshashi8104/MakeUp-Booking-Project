package modules.booking.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

import modules.servicecatalog.entity.ServiceEntity;
import modules.slot.entity.TimeSlot;
import modules.user.entity.User;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private ServiceEntity service;

    @ManyToOne
    private TimeSlot slot;

    private String status; // PENDING, CONFIRMED, CANCELLED

    private LocalDateTime createdAt;

    private LocalDateTime expiresAt;
    
    public Booking() {}

    public Long getId() { return id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public ServiceEntity getService() { return service; }
    public void setService(ServiceEntity service) { this.service = service; }

    public TimeSlot getSlot() { return slot; }
    public void setSlot(TimeSlot slot) { this.slot = slot; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getExpiresAt() { return expiresAt; }
    public void setExpiresAt(LocalDateTime expiresAt) { this.expiresAt = expiresAt; }
}
