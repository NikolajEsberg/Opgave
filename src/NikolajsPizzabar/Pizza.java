package NikolajsPizzabar;


/**
 * Enum Pizza repræsenterer faste værdier – dvs. pizzeriaets menukort.
 * Hver pizza har et nummer og et navn. Brug af enum er ideel, når man har et begrænset, kendt sæt af muligheder.
 */
public enum Pizza {
    // Konstanter defineret én gang, hver med et nummer og navn (immutable objekter)
    MARGHERITA(1, "Margherita"),
    PEPPERONI(2, "Pepperoni"),
    VEGETAR(3, "Vegetar"),
    HAWAII(4, "Hawaii"),
    FJERKRÆ(5, "Fjerkræ"),
    KØDSPESIAL(6, "Kødspesial"),
    MEXICANA(7, "Mexicana"),
    CAPRICCIOSA(8, "Capricciosa"),
    QUATTRO_STAGIONI(9, "Quattro Stagioni"),
    DIAVOLA(10, "Diavola"),
    PROSCIUTTO(11, "Prosciutto"),
    FUNGHI(12, "Funghi"),
    FRUTTI_DI_MARE(13, "Frutti di Mare"),
    BBQ_CHICKEN(14, "BBQ Chicken"),
    ALTONA(15, "Altona"),
    TONNO(16, "Tonno"),
    VEGA(17, "Vega"),
    CALZONE(18, "Calzone"),
    SICILIANA(19, "Siciliana"),
    PARMA(20, "Parma"),
    PESTO(21, "Pesto"),
    SPINAT(22, "Spinat"),
    KEBAB(23, "Kebab"),
    RUCOLA(24, "Rucola"),
    GOURMET(25, "Gourmet"),
    CHILI(26, "Chili"),
    BBQ_BEEF(27, "BBQ Beef"),
    CHEESE_LOVERS(28, "Cheese Lovers"),
    MEDITERRANEAN(29, "Mediterranean"),
    FOUR_CHEESES(30, "Four Cheeses");

    // Felter for hver pizza (privat, som ved almindelige klasser – indkapsling)
    private final int number;
    private final String name;

    /**
     * Konstruktør – bruges internt i enum til at initialisere konstanterne.
     * Enum-konstruktører er altid private, selvom man ikke skriver det.
     */
    Pizza(int number, String name) {
        this.number = number;
        this.name = name;
    }

    // Getter for pizzanummer (bruges fx til inputvalg)
    public int getNumber() {
        return number;
    }

    // Getter for navn (bruges i brugergrænseflade og til udskrivning)
    public String getName() {
        return name;
    }

    /**
     * Hjælpemetode: Find pizza baseret på nummer (brugt i brugerinput).
     * Returnerer null hvis nummeret ikke findes.
     */
    public static Pizza getByNumber(int number) {
        for (Pizza pizza : values()) {
            if (pizza.getNumber() == number) {
                return pizza;
            }
        }
        return null;
    }

    /**
     * Overskrivning af toString – ændrer hvordan en pizza vises i konsollen.
     * F.eks.: "1. Margherita"
     */
    @Override
    public String toString() {
        return number + ". " + name;
    }
}