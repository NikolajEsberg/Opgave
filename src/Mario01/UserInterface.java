package Mario01;

import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;


public class UserInterface {

    private final Menu menu;
    private final OrderList orderList;
    private final FileHandler fileHandler;
    private final String archivePath;
    private final Scanner sc = new Scanner(System.in);

    public UserInterface(Menu menu, OrderList orderList, FileHandler fileHandler, String archivePath) {
        this.menu = menu;
        this.orderList = orderList;
        this.fileHandler = fileHandler;
        this.archivePath = archivePath;
    }
    // Hovedmenu
    public void run() {
        while (true) {
            System.out.println("\n=== Mario's Pizzabar ===");
            System.out.println("1) Show menu");
            System.out.println("2) Create order");
            System.out.println("3) Show active orders (sorted by pickup time)");
            System.out.println("4) Mark order READY");
            System.out.println("5) Mark order PICKED UP");
            System.out.println("6) Archive order");
            System.out.println("7) Save archive to file");
            System.out.println("0) Exit");
            System.out.print("Choose: ");

            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1" -> menu.printMenu();
                case "2" -> createOrder();
                case "3" -> listActive();
                case "4" -> markReady();
                case "5" -> markPickedUp();
                case "6" -> archiveOrder();
                case "7" -> saveArchive();
                case "0" -> { saveOnExit(); return; }
                default -> System.out.println("Unknown choice.");
            }
        }
    }

    // Opret ny ordre
    private void createOrder() {
        try {
            System.out.print("Customer name: ");
            String name = sc.nextLine().trim();

            System.out.print("Pizza number: ");
            int number = Integer.parseInt(sc.nextLine().trim());
            Pizza p = menu.getByNumber(number);
            if (p == null) {
                System.out.println("No pizza with that number.");
                return;
            }

            System.out.print("Pickup time (HH:MM): ");
            LocalTime t = LocalTime.parse(sc.nextLine().trim());

            var order = orderList.addOrder(p, name, t);
            System.out.println("Created: " + order);
        } catch (Exception e) {
            System.out.println("Invalid input. Try again.");
        }
    }

    private void listActive() {
        // Vis aktive ordrer sorteret efter afhentningstid
        List<Order> list = orderList.activeSortedByPickup();
        if (list.isEmpty()) {
            System.out.println("No active orders.");
            return;
        }
        System.out.println("=== Active Orders ===");
        list.forEach(o -> System.out.println(o.toString()));
    }
    // Marker ordre som klar
    private void markReady() {
        System.out.print("Order id to mark READY: ");
        int id = parseInt(sc.nextLine());
        if (orderList.markReady(id)) System.out.println("Order marked READY.");
        else System.out.println("Order not found.");
    }
    // Marker ordre som afhentet
    private void markPickedUp() {
        System.out.print("Order id to mark PICKED UP: ");
        int id = parseInt(sc.nextLine());
        if (orderList.markPickedUp(id)) System.out.println("Order marked PICKED UP.");
        else System.out.println("Order not found.");
    }

    // Arkiver ordre
    private void archiveOrder() {
        System.out.print("Order id to ARCHIVE: ");
        int id = parseInt(sc.nextLine());
        if (orderList.archiveOrder(id)) System.out.println("Order archived.");
        else System.out.println("Order not found.");
    }

    // Gem arkiv til fil
    private void saveArchive() {
        fileHandler.saveArchive(archivePath, orderList.getArchive());
    }

    // Gem ved afslutning
    private void saveOnExit() {
        fileHandler.saveArchive(archivePath, orderList.getArchive());
        System.out.println("Goodbye!");
    }

    private int parseInt(String s) {
        try { return Integer.parseInt(s.trim()); } catch (Exception e) { return -1; }
    }
}
