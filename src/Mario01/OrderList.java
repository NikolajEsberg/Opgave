package Mario01;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// Holder styr på aktive ordrer + arkiverede ordrer
public class OrderList {
    private final List<Order> active = new ArrayList<>();
    private List<Order> archive = new ArrayList<>();

    // Indlæs tidligere arkiv
    public void setArchive(List<Order> archive) {
        if (archive != null) this.archive = archive;
    }

    public List<Order> getActive() { return new ArrayList<>(active); }
    public List<Order> getArchive() { return new ArrayList<>(archive); }

    // Tilføj ny ordre
    public Order addOrder(Pizza pizza, String customer, LocalTime pickup) {
        Order o = new Order(pizza, customer, pickup);
        active.add(o);
        return o;
    }

    // Marker ordre som klar
    public boolean markReady(int orderId) {
        return findActive(orderId).map(o -> { o.setStatus(OrderStatus.READY); return true; }).orElse(false);
    }

    // Marker ordre som afhentet
    public boolean markPickedUp(int orderId) {
        return findActive(orderId).map(o -> { o.setStatus(OrderStatus.PICKED_UP); return true; }).orElse(false);
    }

    // Flyt ordre til arkiv
    public boolean archiveOrder(int orderId) {
        Optional<Order> opt = findActive(orderId);
        if (opt.isPresent()) {
            Order o = opt.get();
            o.setStatus(OrderStatus.ARCHIVED);
            active.remove(o);
            archive.add(o);
            return true;
        }
        return false;
    }

    public boolean removeActive(int orderId) {
        return active.removeIf(o -> o.getId() == orderId);
    }

    // Sorter aktive ordrer efter afhentningstid
    public List<Order> activeSortedByPickup() {
        return active.stream()
                .sorted(Comparator.comparing(Order::getPickupTime))
                .collect(Collectors.toList());
    }

    // Find aktiv ordre ud fra ID
    public Optional<Order> findActive(int orderId) {
        return active.stream().filter(o -> o.getId() == orderId).findFirst();
    }
}
