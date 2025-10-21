package MariosPizzaBar;

import java.time.LocalDateTime;
import java.util.Scanner;

//Main klasse indeholder brugergrænsefladen.
//Brugeren vælger om de er Mario eller Alfonso og får forskellige menuer.
//Her bruger vi inputaliderin og fejlhåndtering.

public class Test {
    public static void main(String[] args) {
        OrderManager orderManager = new OrderManager();

        //Filhåndtering indlæser ordrer fra fil ved programstart.
        if (FileHandler.loadOrders(orderManager)) {
            System.out.println("Order indlæst fra fil.");
        } else {
            System.out.println("Ingen gemte ordrer fundet.");
        }

        //Shutdown hook gemmer ordrer automatisk når programmet afsluttes.
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            FileHandler.saveOrders(
                    orderManager.getActiveOrders(),
                    orderManager.getReadyOrders(),
                    orderManager.getCompletedOrders()
            );
            System.out.println("\n Ordrer gemt før programmet lukkede:");
        }));

        //Scanner bruges til at læse brugerinput fra konsollen
        Scanner scanner = new Scanner(System.in);
        String currentRole = chooseRole(Scanner);

        while (true) {
            if ("Mario".equals(currentRole)) {
                currentRole = marioMenu(orderManager, scanner);
            } else if ("Alfonso".equals(currentRole)) {
                currentRole = alfonsoMenu(orderManager, scanner);
            } else {
                currentRole = chooseRole(scanner);
            }
        }
    }

    //Brug af inputvalidering.
    private static String chooseRole(Scanner scanner) {
        while (true) {
            System.out.println("Er du (1) Mario eller (2) Alfonso?");
            String roleChoice = scanner.nextLine();
            if ("1".equals(roleChoice)) {
                return "Mario";
            } else if ("2".equals(roleChoice)) {
                return "Alfonso";
            } else {
                System.out.println("Ugyldig indtastning. Prøv igen");
            }
        }
    }


}