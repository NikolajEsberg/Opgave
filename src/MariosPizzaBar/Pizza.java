package MariosPizzaBar;
//Enum Pizza repræsentere faste værdier. Altså herunder kan vi se Menukortet for Marios Pizzarier.
//Vi har tilføjet 30 forskellige pizzaer, og hvert pizza består af et nummer, navn og pris.
public enum Pizza { 
    MARGHERITA(1, "Margherita", 90),
    PEPPERONI(2, "Pepperoni", 95),
    VEGETAR(3, "Vegetar", 100),
    HAWAII(4, "Hawaii", 90),
    FJERKRÆ(5, "Fjerkræ", 95),
    KØDSPESIAL(6, "Kødspesial", 100),
    MEXICANA(7, "Mexicana", 90),
    CAPRICCIOSA(8, "Capricciosa", 95),
    QUATTRO_STAGIONI(9, "Quattro Stagioni", 100),
    DIAVOLA(10, "Diavola", 90),
    PROSCIUTTO(11, "Prosciutto", 95),
    FUNGHI(12, "Funghi", 100),
    FRUTTI_DI_MARE(13, "Frutti di Mare", 90),
    BBQ_CHICKEN(14, "BBQ Chicken", 95),
    ALTONA(15, "Altona", 100),
    TONNO(16, "Tonno", 90),
    VEGA(17, "Vega", 95),
    CALZONE(18, "Calzone", 100),
    SICILIANA(19, "Siciliana", 90),
    PARMA(20, "Parma", 95),
    PESTO(21, "Pesto", 100),
    SPINAT(22, "Spinat", 90),
    KEBAB(23, "Kebab", 95),
    RUCOLA(24, "Rucola", 100),
    GOURMET(25, "Gourmet", 90),
    CHILI(26, "Chili", 95),
    BBQ_BEEF(27, "BBQ Beef", 100),
    CHEESE_LOVERS(28, "Cheese Lovers", 90),
    MEDITERRANEAN(29, "Mediterranean", 95),
    FOUR_CHEESES(30, "Four Cheeses", 100);

    //Felterne er erklæret som private for at sikre indkapsling (encapsulation).
    //Det betyder at de kun kan tilgås via klassens egne metoder.
    private final int number;
    private final String name;
    private final double price;


    //Konstruktør - bruges internt i enum til at initialisere konstanterne.
    //Enum-Konstruktør er altid private, selvom man ikke skriver det.
    Pizza(int number, String name, double price) {
        this.number = number;
        this.name = name;
        this.price = price;
    }

//Getter for Pizzanummer (bruges f.eks. til inputvalg)
    public int getNumber() {
        return number;
    }
// Getter for navn (bruges i brugergrænseflade og til udskrivning)
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
//Finder en pizza baseret på det angivne nummer
//Bruges som hjælpemetode ved brugerinput

    public static Pizza getByNumber(int number) {
        for (Pizza pizza : values()) {
            if (pizza.getNumber() == number) {
                return pizza;
            }
        }
        return null;
    }

    //Overskrivning af toString - ændrer hvordan en pizza vises
    //Eksempelvis - 1. Margherita, 90kr
    @Override
    public String toString() {
        return number + ". " + name + ". " + price + "kr";
    }

}