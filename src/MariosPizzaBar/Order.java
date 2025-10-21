package MariosPizzaBar;

import java.io.Serializable;
import java.time.LocalDateTime;

// Repræsentere en ordre i Marios Pizzabar
// implementerer Serializable, så ordren kan gemmes til en fil
public class Order implements Serializable {

    // gør det muligt at gemme objektet
    private static final long serialVersionUID = 1L;

    // Holder styr på, hvilket ID den næste ordre skal have
    private static int nextId = 1;

    // Unikt ID for hver ordre
    private final int id;

    // Den pizza som er bestilt
    private final Pizza pizza;

    // Tidspunkt hvor ordren skal afhentes
    private final LocalDateTime pickupTime;

    // Navn på kunden, der har bestilt
    private final String customerName;

    // Opretter en ny ordre og udeler automatisk et ID
    public Order(Pizza pizza, LocalDateTime pickupTime, String customerName) {
        this.id = nextId++;
        this.pizza = pizza;
        this.pickupTime = pickupTime;
        this.customerName = customerName;
    }

    // Returnerer ordre-ID
    public int getId() { return id; }

    // Returnerer pizzaen i ordren
    public Pizza getPizza() { return pizza; }

    //
    public LocalDateTime getPickupTime() { return pickupTime;}

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
