package NikolajsPizzabar;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Order-klassen repræsenterer en enkelt pizzaordre.
 * Klassen anvender OOP-principper som indkapsling, og understøtter serialisering for filhåndtering.
 */
public class Order implements Serializable {
    // serialVersionUID bruges til at sikre kompatibilitet ved serialisering (filhåndtering)
    private static final long serialVersionUID = 1L;

    // Statisk variabel, der holder styr på det næste unikke ID (bruges i ID-generering)
    private static int nextId = 1;

    // Endelige instansfelter – når de er sat i konstruktøren, kan de ikke ændres
    private final int id;
    private final Pizza pizza;                  // Enum-type (fast værdisæt, OOP-koncept)
    private final LocalDateTime pickupTime;// Tidspunkt for afhentning

    /**
     * Konstruktør – opretter en ny ordre med automatisk ID og brugerinput for pizza og tidspunkt.
     * Anvender indkapsling og initiering via konstruktør.
     *
     * @param pizza       Den valgte pizza (enum Pizza)
     * @param pickupTime  Tidspunkt for afhentning (LocalDateTime – type til tid)
     */
    public Order(Pizza pizza, LocalDateTime pickupTime) {
        this.id = nextId++;        // Automatisk ID-generering
        this.pizza = pizza;
        this.pickupTime = pickupTime;
    }

    // Getter for ID – tillader læsning, men ikke ændring (indkapsling)
    public int getId() {
        return id;
    }

    // Getter for pizza – adgang til hvilken pizza der er bestilt
    public Pizza getPizza() {
        return pizza;
    }

    // Getter for afhentningstid – bruges i sortering og visning
    public LocalDateTime getPickupTime() {
        return pickupTime;
    }

    /**
     * Bruges efter genindlæsning fra fil til at sikre unikke ID’er for nye ordrer.
     * OOP-princip: Statisk metode for kontrol over fælles (shared) variabel.
     *
     * @param next det næste ID der skal bruges
     */
    public static void setNextId(int next) {
        nextId = next;
    }

    /**
     * ToString bruges til at vise ordren i et pænt format i konsollen.
     * Overskriver Object.toString() (polymorfi).
     */
    @Override
    public String toString() {
        return String.format("ID: %d | %s | Afhentes: %s",
                id,
                pizza.getName(),
                pickupTime.toLocalTime());  // Viser kun klokkeslæt, ikke dato
    }
}
