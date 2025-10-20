package NikolajsPizzabar;

import java.util.ArrayList;
import java.util.List;

/**
 * OrderManager er ansvarlig for håndtering af ordrer gennem hele deres livscyklus.
 * Klasserne anvender OOP-principper som indkapsling, abstraktion og ansvarlig metodeopdeling.
 */
public class OrderManager {

    // Brug af Collection API: ArrayList implementerer List-interface.
    // Tre separate lister: aktiv, klar til afhentning, og afsluttet (Completed).
    private List<Order> activeOrders = new ArrayList<>();
    private List<Order> readyOrders = new ArrayList<>();
    private List<Order> completedOrders = new ArrayList<>();

    /**
     * Tilføjer en ny ordre til listen over aktive ordrer.
     * Funktionel krav: Brugeren (Alfonso) kan oprette en ordre.
     *
     * @param order Ordren der skal tilføjes
     */
    public void addOrder(Order order) {
        activeOrders.add(order);
    }

    /**
     * Viser alle aktive ordrer i konsollen.
     * Bruges af Mario til at se, hvilke ordrer der skal laves.
     */
    public void showActiveOrders() {
        if (activeOrders.isEmpty()) {
            System.out.println("Ingen aktive ordrer.");
            return;
        }
        System.out.println("Aktive ordrer:");
        activeOrders.forEach(System.out::println);
    }

    /**
     * Flytter en ordre fra "aktive" til "klar til afhentning".
     * Bruges af Mario efter pizzaen er lavet.
     *
     * @param orderId ID på ordren der skal markeres som klar
     */
    public void markOrderReady(int orderId) {
        Order order = findOrderById(activeOrders, orderId);
        if (order != null) {
            activeOrders.remove(order);
            readyOrders.add(order);
            System.out.println("Ordre markeret som klar til afhentning: " + order);
        } else {
            System.out.println("Ordre ikke fundet i aktive ordrer.");
        }
    }

    /**
     * Viser alle ordrer, som er klar til afhentning.
     * Bruges af Alfonso for at informere kunder ved afhentning.
     */
    public void showReadyOrders() {
        if (readyOrders.isEmpty()) {
            System.out.println("Ingen ordrer klar til afhentning.");
            return;
        }
        System.out.println("Ordrer klar til afhentning:");
        readyOrders.forEach(System.out::println);
    }

    /**
     * Fuldfører en ordre, dvs. kunden har hentet og betalt.
     * Flytter ordren til "completedOrders", så Mario kan lave statistik senere.
     *
     * @param orderId ID på ordren der afsluttes
     */
    public void completeReadyOrder(int orderId) {
        Order order = findOrderById(readyOrders, orderId);
        if (order != null) {
            readyOrders.remove(order);
            completedOrders.add(order);
            System.out.println("Ordre afsluttet og arkiveret: " + order);
        } else {
            System.out.println("Ordre ikke fundet i klar-til-afhentning listen.");
        }
    }

    /**
     * Viser alle afsluttede ordrer, bruges fx til statistik og overblik.
     */
    public void showCompletedOrders() {
        if (completedOrders.isEmpty()) {
            System.out.println("Ingen afsluttede ordrer endnu.");
            return;
        }
        System.out.println("Afsluttede ordrer:");
        completedOrders.forEach(System.out::println);
    }

    /**
     * Hjælpefunktion til at finde en ordre baseret på ID i en given liste.
     * Udnytter Java Streams (moderne funktionel programmering)
     *
     * @param list Liste der skal søges i
     * @param id   Ordre-ID
     * @return Fundet ordre eller null
     */
    private Order findOrderById(List<Order> list, int id) {
        return list.stream()
                .filter(o -> o.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Getters og setters – anvendes især af FileHandler til at læse/gemme til fil

    public List<Order> getActiveOrders() {
        return activeOrders;
    }

    public List<Order> getReadyOrders() {
        return readyOrders;
    }

    public List<Order> getCompletedOrders() {
        return completedOrders;
    }

    public void setActiveOrders(List<Order> activeOrders) {
        this.activeOrders = activeOrders;
    }

    public void setReadyOrders(List<Order> readyOrders) {
        this.readyOrders = readyOrders;
    }

    public void setCompletedOrders(List<Order> completedOrders) {
        this.completedOrders = completedOrders;
    }
}
