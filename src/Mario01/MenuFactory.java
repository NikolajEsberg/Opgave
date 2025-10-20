package Mario01;

// Opretter et standard menukort
public class MenuFactory {

    public static Menu defaultMenu() {
        Menu menu = new Menu();
        menu.addPizza(new Pizza(1,  "Margherita", 75));
        menu.addPizza(new Pizza(2,  "Vesuvio (skinke)", 85));
        menu.addPizza(new Pizza(3,  "Pepperoni", 85));
        menu.addPizza(new Pizza(4,  "Hawaii", 90));
        menu.addPizza(new Pizza(5,  "Capricciosa", 90));
        menu.addPizza(new Pizza(6,  "Quattro Formaggi", 95));
        menu.addPizza(new Pizza(7,  "Diavola (stærk)", 95));
        menu.addPizza(new Pizza(8,  "Prosciutto", 90));
        menu.addPizza(new Pizza(9,  "Funghi", 85));
        menu.addPizza(new Pizza(10, "Calzone (indbagt)", 95));
        menu.addPizza(new Pizza(11, "Carbonara", 95));
        menu.addPizza(new Pizza(12, "Parma & Rucola", 105));
        menu.addPizza(new Pizza(13, "BBQ Chicken", 105));
        menu.addPizza(new Pizza(14, "Kebab Special", 105));
        menu.addPizza(new Pizza(15, "Tuna & Onion", 95));
        menu.addPizza(new Pizza(16, "Vegetar", 90));
        menu.addPizza(new Pizza(17, "Bolognese", 95));
        menu.addPizza(new Pizza(18, "Quattro Stagioni", 105));
        menu.addPizza(new Pizza(19, "Meat Lovers", 110));
        menu.addPizza(new Pizza(20, "Seafood", 115));
        menu.addPizza(new Pizza(21, "Spinach & Feta", 95));
        menu.addPizza(new Pizza(22, "Truffle Mushroom", 120));
        menu.addPizza(new Pizza(23, "Mexicana", 105));
        menu.addPizza(new Pizza(24, "Pesto Chicken", 110));
        menu.addPizza(new Pizza(25, "Salami & Olives", 100));
        menu.addPizza(new Pizza(26, "Artichoke & Ham", 100));
        menu.addPizza(new Pizza(27, "Salsiccia", 110));
        menu.addPizza(new Pizza(28, "Gorgonzola & Pear", 115));
        menu.addPizza(new Pizza(29, "Nordic Laks", 120));
        menu.addPizza(new Pizza(30, "Chef's Special", 125));
        return menu;
    }
}

