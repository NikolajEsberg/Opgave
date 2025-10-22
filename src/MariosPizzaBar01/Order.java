package MariosPizzaBar01;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Repræsenterer en ordre i Marios Pizzabar
// Implementerer Serializable, så ordren kan gemmes til en fil
public class Order implements Serializable {

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

    // Opretter en ny ordre og uddelegerer automatisk et ID
    public Order(Pizza pizza, LocalDateTime pickupTime, String customerName) {
        this.id = nextId++;
        this.pizza = pizza;
        this.pickupTime = pickupTime;
        this.customerName = customerName;
    }

    // Getter for ID
    public int getId() { return id; }

    // Getter for pizza
    public Pizza getPizza() { return pizza; }

    // Getter for afhentningstidspunkt
    public LocalDateTime getPickupTime() { return pickupTime; }

    // Setter til at genskabe ID efter indlæsning fra fil
    public static void setNextId(int next) { nextId = next; }

    // Overskriver toString for at vise ordren på en pæn måde
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return ("ID: " + id + " | Kunde: " + customerName + "| Pizza: " + pizza.getName() + " " +  pizza.getPrice() +" kr. " + "Afhentning: " + pickupTime.toLocalTime().format(formatter));

    }
}
