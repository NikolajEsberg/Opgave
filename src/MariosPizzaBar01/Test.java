package MariosPizzaBar01;

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
            System.out.println("Ingen gemte ordrer fundet. Starter frisk.");
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
        String currentRole = chooseRole(scanner);  // Mario eller Alfonso
//While true loop der sørger for den rigtige menu bliver vist.
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

    //Menu for Mario
    private static String marioMenu(OrderManager orderManager, Scanner scanner) {
        while (true) {
            System.out.println("\n[MARIO] Hvad vil du gøre?");
            System.out.println("1. Se aktive ordrer");
            System.out.println("2. Markér ordre som klar til afhentning");
            System.out.println("3. Vis afsluttede ordrer");
            System.out.println("4. Skift bruger til Alfonso");
            System.out.println("5. Afslut program");

            String choice = scanner.nextLine();
//switch
            switch (choice) {
                case "1":
                    orderManager.showActiveOrders();
                    break;
                case "2":
                    System.out.print("Indtast ordre-ID som er klar: ");
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
                    System.exit(0);  // 💥 Lukker programmet
                default:
                    System.out.println("Ugyldigt valg.");
            }
        }
    }
//Hector
    //Menu for Alfanso

    //Den bruger et while (true)-loop, hvilket betyder at menuen kører i en uendelig løkke, indtil der sker et “break” eller return et sted senere i koden.
    private static String alfonsoMenu(OrderManager orderManager, Scanner scanner) {
        while (true) {
            //her vises hovedmenuen
            System.out.println("\n[ALFONSO] Hvad vil du gøre?");
            System.out.println("1. Vis menukort");
            System.out.println("2. Opret ny ordre");
            System.out.println("3. Se ordrer klar til afhentning");
            System.out.println("4. Markér ordre som hentet");
            System.out.println("5. Skift bruger til Mario");
            System.out.println("6. Afslut program");
            //Læser brugerens valg som tekst, da det er en "String Choice"
            String choice = scanner.nextLine();
            //Switch-statment bruges til at håndtere de forskellige menupunkter
            //AGAH
            switch (choice) {
                case "1":
                    // Viser alle pizzaer i menukortet, fra Enum.
                    for (Pizza p : Pizza.values()) {
                        System.out.println(p);
                    }
                    break;

                case "2":
                    //Her kan de oprette en ny ordre, når de indtaster pizzanumeret.
                    try {
                        System.out.print("Indtast pizzanummer: ");
                        int pizzaNr = Integer.parseInt(scanner.nextLine());
                        //finder pizzaen ud fra nummeret
                        Pizza pizza = Pizza.getByNumber(pizzaNr);
                        if (pizza == null) {
                            System.out.println("Ugyldigt pizzanummer.");
                            break; //If pizza == null springer ud af case, hvis nummeret ikke eksistere.
                        }

                        // Deler tidspunktet op i timer og minutter.
                        System.out.print("Indtast afhentningstid (HH:MM): ");
                        String timeInput = scanner.nextLine();
                        String[] parts = timeInput.split(":");
                        int hour = Integer.parseInt(parts[0]);
                        int minute = Integer.parseInt(parts[1]);
                        // her oprettes der et localDateTime-Objekt til afhentningstiden.
                        LocalDateTime pickup = LocalDateTime.now()
                                .withHour(hour)
                                .withMinute(minute)
                                .withSecond(0)
                                .withNano(0);
                        //Alfonso indtaster kundens navn her
                        System.out.print("Indtast dit navn: ");
                        String customerName = scanner.nextLine();
                        //Opretter ny ordre og tilføjer den til OrderManager
                        Order order = new Order(pizza, pickup, customerName);
                        orderManager.addOrder(order);
                        //Bekræftelse af at ordren blev oprettet.
                        System.out.println("Ordre oprettet: " + order);
                    } catch (Exception e) {
                        //fanger fejl ved brugerinput (fe.sk. forkert format)
                        System.out.println("Fejl i input: " + e.getMessage());
                    }
                    break;

                case "3":
                    //viser alle ordrer der er klar til afhentning
                    orderManager.showReadyOrders();
                    break;

                case "4":
                    //markere en ordre som afhentet
                    System.out.print("Indtast ordre-ID som er hentet: ");
                    try {
                        int id = Integer.parseInt(scanner.nextLine());
                        orderManager.completeReadyOrder(id);
                    } catch (NumberFormatException e) {
                        System.out.println("Ugyldigt ID.");
                    }
                    break;

                case "5":
                    //her vælger alfonoso eller mario at skifte til mario-menuen
                    return "Mario";
                //afslutter hele programmet.
                case "6":
                    System.out.println("Farvel!");
                    System.exit(0);

                default:
                    System.out.println("Ugyldigt valg.");
            }
        }
    }
}
