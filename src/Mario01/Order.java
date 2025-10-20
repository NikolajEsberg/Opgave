package Mario01;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicInteger;

// Repræsenterer en kundebestilling
public class Order {
    // Giver hvert ordre et unikt ID
    private static final AtomicInteger ID_SEQ = new AtomicInteger(1000); // Dansk: simpel id-generator

    private final int id;
    private final Pizza pizza;
    private final String customerName;
    private final LocalTime pickupTime;
    private final LocalDateTime createdAt;
    private OrderStatus status;

    // Opret ny ordre
    public Order(Pizza pizza, String customerName, LocalTime pickupTime) {
        this.id = ID_SEQ.getAndIncrement();
        this.pizza = pizza;
        this.customerName = customerName;
        this.pickupTime = pickupTime;
        this.createdAt = LocalDateTime.now();
        this.status = OrderStatus.ORDERED;
    }
    // Getters
    public int getId() { return id; }
    public Pizza getPizza() { return pizza; }
    public String getCustomerName() { return customerName; }
    public LocalTime getPickupTime() { return pickupTime; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public OrderStatus getStatus() { return status; }

    // Skift status
    public void setStatus(OrderStatus status) { this.status = status; }

    // Tekstvisning af ordren
    @Override
    public String toString() {
        return String.format("#%d [%s] %s kl. %s (%.2f kr) - %s",
                id,
                pizza.getNumber() + " " + pizza.getName(),
                customerName,
                pickupTime,
                pizza.getPrice(),
                status);
    }
}

