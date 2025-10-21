package MariosPizzaBar;

import java.time.LocalDateTime;
import java.util.Scanner;


//Main klassen indeholder brugergrænsefladen.
//Brugeren vælger, om de er Mario eller Alfonso, og får forskellige menuer.
//Her bruges inputvalidering, fejlhåndtering.

public class Test {
    public static void main(String[] args) {
        OrderManager orderManager = new OrderManager();

        //Filhåndtering indlæsr ordrer fra fil ved programstart
        if (FileHandler.loadOrders(orderManager)) {
            System.out.println("Ordrer indlæst fra fil.");
        } else {
            System.out.println("Ingen gemte ordrer fundet.");
        }

        // Shutdown hook gemmer ordrer automatisk når programmet afsluttes
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            FileHandler.saveOrders(
                    orderManager.getActiveOrders(),
                    orderManager.getReadyOrders(),
                    orderManager.getCompletedOrders()
            );
            System.out.println("\nOrdrer gemt før programmet lukkede.");
        }));

        //Scanner bruges til at læse brugerinput fra konsollen
        Scanner scanner = new Scanner(System.in);
        String currentRole = chooseRole(scanner);

        while (true) {
            if ("Mario".equals(currentRole)) {
                currentRole = marioMenu(orderManager, scanner);
            } else if ("Alfonso".equals(currentRole)) {
                currentRole = alfonsoMenu(orderManager, scanner);
            } else {
                currentRole = chooseRole(scanner);  // fallback
            }
        }
    }

     //Brugeren vælger om de er Mario eller Alfonso.
     //Brug af inputvalidering og fejlhåndtering.
    private static String chooseRole(Scanner scanner) {
        while (true) {
            System.out.println("Er du (1) Mario eller (2) Alfonso?");
            String roleChoice = scanner.nextLine();
            if ("1".equals(roleChoice)) {
                return "Mario";
            } else if ("2".equals(roleChoice)) {
                return "Alfonso";
            } else {
                System.out.println("Ugyldig indtastning. Prøv igen.");
            }
        }
    }

    private static String marioMenu(OrderManager orderManager, Scanner scanner) {
        while (true) {
            System.out.println("\n[MARIO] Hvad vil du gøre?!?");
            System.out.println("1. Se aktive ordrer");
            System.out.println("2. Marker ordre som klar til afhentning");
            System.out.println("3. Vis afsluttede ordrer");
            System.out.println("4. Skift bruger til Alfonso");
            System.out.println("5. Afslut program");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    orderManager.showActiveOrders();
                    break;
                case "2":
                    System.out.print("Indtast ordre-Id som er klar: ");
                    try {
                        int id = Integer.parseInt(scanner.nextLine());
                        orderManager.markOrderReady(id);
                    } catch (NumberFormatException e) {
                        System.out.println("Ugyldigt ID.");
                    }
                    break;
                case "3":
                    orderManager.showCompletedOrders();
                    break;
                case "4":
                    return "Alfonso";
                case "5":
                    System.out.println("Farvel!");
                    System.exit(0);
                default:
                    System.out.println("Ugyldigt valg");
            }
        }
    }