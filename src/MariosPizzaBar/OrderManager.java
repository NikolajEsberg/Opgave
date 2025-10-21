package MariosPizzaBar;

import java.util.ArrayList;
import java.util.List;

// Håndterer alle ordrer: aktive, klart afhentning og afsluttede
public class OrderManager {

    // lister til at gemme ordre i forskellige stadier
    private List<Order> activeOrders = new ArrayList<>();
    private List<Order> readyOrders = new ArrayList<>();
    private List<Order> completedOrders = new ArrayList<>();

    // Tilføjer en ny ordre til de aktive ordrer
    public void addOrder(Order order) {
        activeOrders.add(order);
    }

    // Viser alle aktive ordrer
    public void showActiveOrders() {
        if (activeOrders.isEmpty()) {
            System.out.println("Ingen aktive ordrer.");
            return;
        }
        System.out.println("Aktive ordrer:");
        activeOrders.forEach(System.out::println);
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

    // Viser alle ordre der er klar til afhentning
    public void showReadyOrders() {
        if (readyOrders.isEmpty()) {
            System.out.println("ingen ordrer klar til afhentning. ");
            return;
        }
        System.out.println("Ordrer klar til afhentning:");
        readyOrders.forEach(System.out::println);
    }

    // Markerer en klar ordre som afluttet og fflytte den til arkivet
    public void completeReadyOrder(int orderId){
        Order order = findOrderById(readyOrders, orderId);
        if (order != null) {
            readyOrders.remove(order);
            completedOrders.add(order);
            System.out.println("Ordre afsluttet og arkiveret: " + order);
        } else {
            System.out.println("Ordre ikee fundet i klar-til-afhenting listen. ");
        }
    }

    // Viser alle færdige ordrer
    public void showCompletedOrders() {
        if (completedOrders.isEmpty()) {
            System.out.println("Ingen afsluttede ordrer endnu.");
            return;
        }
        System.out.println("Afsluttede ordrer: ");
        completedOrders.forEach(System.out::println);
    }

    // finder en ordre ud fra dens ID i en bestemt liste
    private Order findOrderById(List<Order> list,int id) {
        return list.stream()
                .filter(o -> o.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Getters som bruges til at hente listerne udenfor klassen
    public List<Order> getActiveOrders() { return activeOrders; }
    public List<Order> getReadyOrders() { return readyOrders; }
    public List<Order> getCompletedOrders() { return completedOrders; }

    // Setters som bruger hvis man fx skal indlæse data fra en fil
    public void setActiveOrders(List<Order> activeOrders) { this.activeOrders = activeOrders; }
    public void setCompletedOrders(List<Order> completedOrders) { this.completedOrders = completedOrders;}
}

