package MariosPizzaBar;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * FileHandler-klassen håndterer læsning og skrivning af ordrer til og fra en fil.
 * Klassen benytter sig af objekt-serialisering til at gemme ordrer lokalt.
 * Bruges til at sikre vedvarende data (persistent storage) uden netværk.
 */
public class FileHandler {

    // Konstant (final): Sikrer at filnavnet ikke utilsigtet ændres
    private static final String FILE_NAME = "orders.dat";

    /**
     * Gemmer lister af ordrer til en fil ved hjælp af objekt-serialisering.
     *
     * @param active   Liste af aktive ordrer (Collection API: ArrayList<Order>)
     * @param ready    Liste af ordrer klar til afhentning
     * @param completed Liste af afsluttede ordrer
     */
    public static void saveOrders(List<Order> active, List<Order> ready, List<Order> completed) {
        // Try-with-resources sikrer automatisk lukning af stream (god praksis for ressourcehåndtering)
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            // Skriver hver liste til filen i rækkefølge
            oos.writeObject(active);
            oos.writeObject(ready);
            oos.writeObject(completed);
        } catch (IOException e) {
            // Fejlhåndtering: Programmet håndterer IOException uden at gå ned (kravopfyldelse)
            System.out.println("Fejl ved gemning af ordrer: " + e.getMessage());
        }
    }

    /**
     * Indlæser gemte ordrer fra fil og indstiller dem i OrderManager.
     *
     * @param manager OrderManager-objektet, som skal opdateres med indlæste ordrer
     * @return true hvis indlæsningen lykkes, ellers false
     */
    public static boolean loadOrders(OrderManager manager) {
        File file = new File(FILE_NAME);
        // Kontrollerer om filen findes, før den forsøges åbnet (inputvalidering og fejlhåndtering)
        if (!file.exists()) return false;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            // Læser de tre lister i samme rækkefølge som de blev gemt
            List<Order> active = (List<Order>) ois.readObject();
            List<Order> ready = (List<Order>) ois.readObject();
            List<Order> completed = (List<Order>) ois.readObject();

            // Indkapsling: Sætter værdierne via set-metoder i stedet for direkte adgang
            manager.setActiveOrders(active);
            manager.setReadyOrders(ready);
            manager.setCompletedOrders(completed);

            // Beregner højeste ordre-ID, så næste ordre får korrekt ID (Statisk variabelstyring)
            int highestId = 0;
            for (Order o : active) highestId = Math.max(highestId, o.getId());
            for (Order o : ready) highestId = Math.max(highestId, o.getId());
            for (Order o : completed) highestId = Math.max(highestId, o.getId());

            Order.setNextId(highestId + 1); // OOP-princip: Statisk metode til at styre ID-generering

            return true; // Indlæsning lykkedes
        } catch (IOException | ClassNotFoundException e) {
            // Kombineret fejlhåndtering: håndterer både IO-fejl og fejl i objekt-deserialisering
            System.out.println("Fejl ved indlæsning af ordrer: " + e.getMessage());
            return false;
        }
    }
}
