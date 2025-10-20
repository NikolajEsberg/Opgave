package Mario01;

// Repræsenterer information om pizza på menukortet
public class Pizza {
    private final int number;
    private final String name;
    private final double price;

    // Opretter en ny pizza
    public Pizza(int number, String name, double price) {
        this.number = number;
        this.name = name;
        this.price = price;
    }

    // Henter værdier
    public int getNumber() { return number; }
    public String getName() { return name; }
    public double getPrice() { return price; }

    // Viser pizza som tekst
    @Override
    public String toString() {
        // Vis som "12. Margherita - 75.00 kr"
        return number + ". " + name + " - " + String.format("%.2f kr", price);
    }
}

