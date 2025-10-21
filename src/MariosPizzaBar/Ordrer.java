package MariosPizzaBar;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Ordrer implements Serializable {

    private static final long serialVerisonUID = 1L;

    private static int nextId = 1;

    private final int id;
    private final Pizza pizza;
    private final LocalDateTime pickupTime;
    private final String customerName;

    public Order(Pizza pizza, LocalDateTime pickuptime){
        this.id = nextId++;
        this.pizza = pizza;
        this.pickupTime = pickupTime;
        this.customerName = customerName;
    }

    public int getId() { return id; }

    public Pizza getPizza() { return pizza }

    public LocalDateTime getPickupTime() { return pickupTime; }

    public getCustomerName() { return customerName; }

    public static void setNextId(int next) { nextId = next; }

    @Override
    public String toString() {
        return String.format("ID: %d | %s | Afhentes: %s",
                id,
                pizza.getName(),
                customerName,
                pickupTime.toLocalTime());
    }
}
