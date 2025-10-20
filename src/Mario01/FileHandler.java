package Mario01;

import java.io.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    // Gem arkiverede ordrer som CSV
    public void saveArchive(String path, List<Order> archive) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path))) {
            // CSV-header
            pw.println("id,pizzaNumber,pizzaName,price,customer,pickupTime,createdAt,status");
            for (Order o : archive) {
                pw.printf("%d,%d,%s,%.2f,%s,%s,%s,%s%n",
                        o.getId(),
                        o.getPizza().getNumber(),
                        escape(o.getPizza().getName()),
                        o.getPizza().getPrice(),
                        escape(o.getCustomerName()),
                        o.getPickupTime(),
                        o.getCreatedAt(),
                        o.getStatus());
            }
            System.out.println("Archive saved to: " + path);
        } catch (IOException e) {
            System.out.println("Failed to save archive: " + e.getMessage());
        }
    }

    // Indlæs arkiverede ordrer fra CSV
    public List<Order> loadArchive(String path) {
        List<Order> list = new ArrayList<>();
        File f = new File(path);
        if (!f.exists()) return list;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine(); // spring header over
            while ((line = br.readLine()) != null) {
                String[] parts = splitCsv(line);
                if (parts.length < 8) continue;

                // Rekonstruer kun læsbar visning
                int id = parseInt(parts[0], -1);
                int pizzaNo = parseInt(parts[1], 0);
                String pizzaName = unescape(parts[2]);
                double price = parseDouble(parts[3], 0);
                String customer = unescape(parts[4]);
                LocalTime pickup = LocalTime.parse(parts[5]);
                LocalDateTime createdAt = LocalDateTime.parse(parts[6]);
                OrderStatus status = OrderStatus.valueOf(parts[7]);

                Pizza p = new Pizza(pizzaNo, pizzaName, price);
                Order o = new Order(p, customer, pickup);
                // Status gemmes
                o.setStatus(status);
                list.add(o);
            }
            System.out.println("Archive loaded from: " + path);
        } catch (Exception e) {
            System.out.println("Failed to load archive: " + e.getMessage());
        }
        return list;
    }

    // ======= Hjælpermetoder (CSV) =======

    // Gør tekst klar til at blive gemt i en CSV-fil
    // Tilføjer amførelstegn omkring teksten, hvis den indeholder komma eller citationstegn
    private String escape(String s) {
        if (s.contains(",") || s.contains("\"")) {
            return "\"" + s.replace("\"", "\"\"") + "\"";
        }
        return s;
    }

    // Fjerner ekstra anførelstegn fra tekst, når den læses fra CSV
    private String unescape(String s) {
        s = s.trim();
        if (s.startsWith("\"") && s.endsWith("\"")) {
            s = s.substring(1, s.length()-1).replace("\"\"", "\"");
        }
        return s;
    }

    // Deler en CSV-linje korrekt op i værdier
    // tager højde for, at nogle værdier kan være omgivet af anførelstegn
    private String[] splitCsv(String line) {
        ArrayList<String> parts = new ArrayList<>();
        boolean inQuotes = false;
        StringBuilder sb = new StringBuilder();
        for (char c : line.toCharArray()) {
            if (c == '\"') {               // Skrift mellem "inde i tekst" og "udenfor tekst"
                inQuotes = !inQuotes;
                sb.append(c);
            } else if (c == ',' && !inQuotes) {  // Hvis komma uden for anførelstegn, afslut feltet
                parts.add(sb.toString());
                sb.setLength(0);
            } else {                        // Ellers tilføj tegnet til det aktuelle felt
                sb.append(c);
            }
        }
        parts.add(sb.toString());           // Tilføj sidste felt
        return parts.toArray(new String[0]);
    }

    // konvertere tekst til heltal
    private int parseInt(String s, int def) {
        try { return Integer.parseInt(s); } catch (Exception e) { return def; }
    }

    // konvertere tekst til decimaltal
    private double parseDouble(String s, double def) {
        try { return Double.parseDouble(s); } catch (Exception e) { return def; }
    }
}
