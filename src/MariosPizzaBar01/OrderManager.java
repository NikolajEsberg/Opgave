package MariosPizzaBar01;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// Håndterer alle ordrer: aktive, klar til afhentning og afsluttede
public class OrderManager {

    // Lister til at gemme ordrer i forskellige stadier
    private List<Order> activeOrders = new ArrayList<>();
    private List<Order> readyOrders = new ArrayList<>();
    private List<Order> completedOrders = new ArrayList<>();

    // Tilføjer en ny ordre til de aktive ordrer
    public void addOrder(Order order) {
        activeOrders.add(order);
    }

    // Viser alle aktive ordrer (sorteret efter afhentningstid)
    public void showActiveOrders() {
        if (activeOrders.isEmpty()) {
            System.out.println("Ingen aktive ordrer.");
            return;
        }
        System.out.println("Aktive ordrer (sorteret efter afhentningstid):");
        activeOrders.stream()
                .sorted(Comparator.comparing(Order::getPickupTime))
                .forEach(System.out::println);
    }

    // Markerer en ordre som klar til afhentning
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

    // Viser ordrer klar til afhentning (sorteret)
    public void showReadyOrders() {
        if (readyOrders.isEmpty()) {
            System.out.println("Ingen ordrer klar til afhentning.");
            return;
        }
        System.out.println("Klar-til-afhentning ordrer (sorteret efter afhentningstid):");
        readyOrders.stream()
                .sorted(Comparator.comparing(Order::getPickupTime))
                .forEach(System.out::println);
    }

    // Markerer en klar ordre som afsluttet og flytter den til arkivet
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

    // Viser alle afsluttede ordrer (sorteret)
    public void showCompletedOrders() {
        if (completedOrders.isEmpty()) {
            System.out.println("Ingen afsluttede ordrer endnu.");
            return;
        }
        System.out.println("Afsluttede ordrer (sorteret efter afhentningstid):");
        completedOrders.stream()
                .sorted(Comparator.comparing(Order::getPickupTime))
                .forEach(System.out::println);
    }

    // Finder en ordre ud fra dens ID i en bestemt liste
    private Order findOrderById(List<Order> list, int id) {
        return list.stream()
                .filter(o -> o.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Gettere til brug i fx filhåndtering
    public List<Order> getActiveOrders() { return activeOrders; }
    public List<Order> getReadyOrders() { return readyOrders; }
    public List<Order> getCompletedOrders() { return completedOrders; }

    // Settere til brug ved indlæsning fra fil
    public void setActiveOrders(List<Order> activeOrders) { this.activeOrders = activeOrders; }
    public void setReadyOrders(List<Order> readyOrders) { this.readyOrders = readyOrders; }
    public void setCompletedOrders(List<Order> completedOrders) { this.completedOrders = completedOrders; }
}
