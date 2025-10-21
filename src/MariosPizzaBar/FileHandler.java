package MariosPizzaBar;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

//Klassen sørger for at vi kan læse og skrive en ordrer fra/til en fil.
//Klassen benytter sig af objekt serialisering til at gemme ordre lokalt.
//Klassen kan gemme i fil uden netværk (Gammel Del-Laptop).

public class FileHandler {

    //Laver en konstant, hvilket sikrer at filnavnet ikke ændres utilsigtet.

    private static final String FILE_NAME = "orders.dat";

    //Gemmer lister af ordrer til en fil ved hjælp af objekt serialisering.
    //Liste "active" viser en liste af alle aktive ordrer.
    //Liste "ready" viser en liste af alle ordrer klar til afhentning.
    //Liste "completed" viser en liste af alle afsluttede ordrer.

    public static void saveOrders(List<Order> active, List<Order> ready, List<Order> completed) {
        //try sørger for automatisk lukning af fil.
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            //Skriver hver liste til filen i rækkefølge.
            oos.writeObject(active);
            oos.writeObject(ready);
            oos.writeObject(completed);
        } catch (IOException e) {
            //Fejlhåndtering: Programmet håndterer IOException uden at gå ned (kravopfyldelse).
            System.out.println("Fejl ved gemning af ordrer: " + e.getMessage());
        }
    }

    //Indlæser gemte ordrer fra ovenstående fil og skriver dem til OrderManager.
    public static boolean loadOrders(OrderManager manager) {
        File file = new File(FILE_NAME);
        //Kontrollere om filen findes, før den bliver forsøgt at åbnes (input validering og fejlhåndtering).
        if (!file.exists()) return false;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            //Læser de 3 lister i samme rækkefølge som de blev gemt
            List<Order> active = (List<Order>) ois.readObject();
            List<Order> ready = (List<Order>) ois.readObject();
            List<Order> completed = (List<Order>) ois.readObject();

            //Indkapsling sætter værdierne via set metoder.
            manager.setActiveOrders(active);
            manager.setReadyOrders(ready);
            manager.setCompletedOrders(completed);

            //Beregner højeste ordrer ID.
            int highestId = 0;
            for (Order o : active) highestId = Math.max(highestId, o.getId());
            for (Order o : ready) highestId = Math.max(highestId, o.getId());
            for (Order o : completed) highestId = Math.max(highestId, o.getId());

            Order.setNextId(highestId + 1);  //OOP princip styrer ID genering.

            return true;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Fejl ved indlæsning af ordrer: " + e.getMessage());
            return false;
        }
    }
}
