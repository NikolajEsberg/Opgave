package MariosPizzaBar;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    private static int nextId = 1;

    private final int id;
    private final Pizza pizza;
    private final LocalDateTime pickupTime;
    private final String customerName;

    public Order(Pizza pizza, LocalDateTime pickupTime) {
        this.id = nextId++;
        this.pizza = pizza;
        this.pickupTime = pickupTime;
        this.customerName = customerName;
    }

    public int getId() { return id; }

    public Pizza getPizza() { return pizza; }

    public LocalDateTime getPickupTime() { return pickupTime}

    public static void setNextId(int next) { nextId = next; }

    @Override
    public String toString(){
        return String.format("ID: %d | %s | Afhentes: %s",
                id,
                customerName,
                pizza.getName(),
                pickupTime.toLocalTime());
    }
}
