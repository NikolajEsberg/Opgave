package Mario01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Holder listen over pizzaer
public class Menu {
    private final List<Pizza> pizzas = new ArrayList<>();

    // Tilføj pizza til menuen
    public void addPizza(Pizza pizza) {
        pizzas.add(pizza);
    }
    // Returnerer listen over pizzaer
    public List<Pizza> getPizzas() {
        return Collections.unmodifiableList(pizzas);
    }
    // Finder pizza ud fra nummer
    public Pizza getByNumber(int number) {
        return pizzas.stream()
                .filter(p -> p.getNumber() == number)
                .findFirst()
                .orElse(null);
    }
    // Skriver hele menuen
    public void printMenu() {
        System.out.println("=== MENU ===");
        pizzas.forEach(p -> System.out.println(p.toString()));
    }
}

