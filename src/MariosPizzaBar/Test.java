package MariosPizzaBar;

import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * Main-klassen der indeholder brugergrænsefladen (CLI).
 * Brugeren vælger, om de er Mario eller Alfonso, og får forskellige menuer.
 * Her ses brug af inputvalidering, fejlhåndtering og systemdesign med klare roller.
 */
public class Test {
    public static void main(String[] args) {
        // Objekt af OrderManager: indeholder forretningslogik og datastruktur
        OrderManager orderManager = new OrderManager();

        // 🔄 Filhåndtering: Indlæs ordrer fra fil ved programstart
        if (FileHandler.loadOrders(orderManager)) {
            System.out.println("✅ Ordrer indlæst fra fil.");
        } else {
            System.out.println("🔄 Ingen gemte ordrer fundet. Starter frisk.");
        }

        // 💾 Shutdown hook: Gem ordrer automatisk når programmet afsluttes
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            FileHandler.saveOrders(
                    orderManager.getActiveOrders(),
                    orderManager.getReadyOrders(),
                    orderManager.getCompletedOrders()
            );
            System.out.println("\n💾 Ordrer gemt før programmet lukkede.");
        }));

        // 🔢 Scanner bruges til at læse brugerinput fra konsollen
        Scanner scanner = new Scanner(System.in);
        String currentRole = chooseRole(scanner);  // Mario eller Alfonso

        // 🔁 Uendelig løkke med dynamisk menunavigation baseret på brugerrolle
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

    /**
     * Brugeren vælger om de er Mario eller Alfonso.
     * Brug af inputvalidering og fejlhåndtering.
     */
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