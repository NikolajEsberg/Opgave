package MariosPizzaBar;

import java.util.ArrayList;
import java.util.List;

public class OrderManager {

    private List<Order> activeOrders = new ArrayList<>();
    private List<Order> readyOrders = new ArrayList<>();
    private List<Order> completedOrders = new ArrayList<>();

    public void addOrder(Order order) {
        activeOrders.add(order);
    }

    public void showActiveOrders() {
        if (activeOrders.isEmpty()) {
            System.out.println("Ingen aktive ordrer.");
            return;
        }
        System.out.println("Aktive ordrer:");
        activeOrders.forEach(System.out::println);
    }


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

    public void showReadyOrders() {
        if (readyOrders.isEmpty()) {
            System.out.println("ingen ordrer klar til afhentning. ");
            return;
        }
        System.out.println("Ordrer klar til afhentning:");
        readyOrders.forEach(System.out::println);
    }


    public void completeReadyOrder(int orderId){
        Order order = findOrderById(readyOrders, orderId);
        if (order != null) {
            readyOrders.remove(order);
            completedOrders.add(order);
            System.out.prinln("Ordre afsluttet og arkiveret: " + order);
        } else {
            System.out.println("Ordre ikee fundet i klar-til-afhenting listen. ");
        }
    }

    public void showCompletedOrders() {
        if (completedOrders.isEmpty()) {
            System.out.println("Ingen afsluttede ordrer endnu.");
            return;
        }
        System.out.println("Afsluttede ordrer: ");
        completedOrders.forEach(System.out::println);
    }

    private Order findORderById(List<Order> list,int id) {
        return list.stream()
                .filter(o -> o.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Order> getActiveOrders() { return activeOrders; }

    public List<Order> getReadyOrders() { return readyOrders; }

    public List<Order> getCompletedOrders() { return completedOrders; }

    public void setActiveOrders(List<Order> activeOrders) { this.activeOrders = activeOrders; }

    public void setCompletedOrders(List<Order> completedOrders) { this.completedOrders = completedOrders;}
}

