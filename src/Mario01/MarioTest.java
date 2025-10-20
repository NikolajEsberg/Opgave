package Mario01;

import java.nio.file.Paths;

public class MarioTest {

    public static void main(String[] args) {
        // Opret menu, ordreliste og filhåndtering
        Menu menu = MenuFactory.defaultMenu();              // 30 pizzaer
        OrderList orderList = new OrderList();              // Aktive + arkiv
        FileHandler fileHandler = new FileHandler();        // Filhåndtering (CSV)

        // Indlæs tidligere arkiverede ordrer
        String archivePath = Paths.get("orders_archive.csv").toAbsolutePath().toString();
        orderList.setArchive(fileHandler.loadArchive(archivePath));

        // Start brugerfladen
        UserInterface ui = new UserInterface(menu, orderList, fileHandler, archivePath);
        ui.run();
    }
}
