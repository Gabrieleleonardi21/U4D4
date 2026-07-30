import entities.Customer;
import entities.Order;
import entities.Customer;
import entities.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class Main {
    static void main(String[] args) {

        List<Product> products = new ArrayList<>(Arrays.asList(
                new Product(1L, "Guida java", "Books", 45.00),
                new Product(2L,"Claude Code", "AI", 20),
                new Product(3L, "Tastiera","Hardware",50),
                new Product(4L, "Netflix","Streaming",19.99),
                new Product(5L,"PSN","Boys",149.99),
                new Product(6L,"Biberon","Baby",8.50),
                new Product(7L,"Passeggino","Baby",49.99),
                new Product(8L, "Il Signore Degli Anelli","Books",50.00),
                new Product(9L,"Eragon","Books",20.00)
        ));

        Customer Aldo = new Customer(1L,"Aldo Baglio", 1);
        Customer Giovanni = new Customer(2L,"Giovanni Storti", 2);
        Customer Giacomo = new Customer(3L,"Giacomo Poretti", 3);
        Customer Francesco = new Customer(4L,"Francesco Totti", 4);
        List<Customer> customers = new ArrayList<>(Arrays.asList(Aldo , Giacomo, Giovanni, Francesco));


        List<Order> orders = new ArrayList<>(Arrays.asList(
                new Order(101L, "Consegnato",
                        LocalDate.of(2026,7,5),LocalDate.of(2026, 7, 8),
                        Arrays.asList(products.get(0), products.get(1)), Aldo),
                new Order(102L, "Spedito",
                        LocalDate.of(2026,7,12), LocalDate.of(2026, 7, 15),
                        Arrays.asList(products.get(5), products.get(3)), Giovanni),
                new Order(103L, "Pendente",
                        LocalDate.of(2026,7,12), null,
                        Arrays.asList(products.get(6)), Giacomo),
                new Order(104L, "Spedito",
                        LocalDate.of(2026,6,25), LocalDate.of(2026, 6, 28),
                        Arrays.asList(products.get(5), products.get(3)), Francesco)

        ));
        System.out.println(products);

        System.out.println("\n===Aggiunta===");
        System.out.println("prodotti prima: "+ products.size());

        Product nuovo = new Product(8L, "Set Lego", "Boys", 49.99);
        products.add(nuovo);

        System.out.println("Prodotti dopo: " + products.size());
        System.out.println("Aggiunto -> " + nuovo);

        System.out.println("\n===Rimozione==");

        Product daRimuovere = products.get(2); //Tastiera
        System.out.println("Sto rimuovendo ->" + daRimuovere);

        boolean rimosso = products.remove(daRimuovere);
        System.out.println("Riuscita?" + rimosso);
        System.out.println("Prodotti ora? " + products.size());

        System.out.println("\n===Contains===");
        System.out.println("La tastiera è ancora presente?" + products.contains(daRimuovere));
        System.out.println("Il set lego è stato aggiunto?" + products.contains(nuovo));


        System.out.println("\n===Lettura e Sostituzione");

        int posizione = 1;
        Product vecchio = products.get(posizione);
        System.out.println("In posizione " + posizione + " c'era -> " + vecchio);

        Product sostituto = new Product(9L, "Refactoring", "Software", 500);
        Product sostituito = products.set(posizione , sostituto);

        System.out.println("Ora in posizione " + posizione + " c'è -> " + products.get(posizione));
        System.out.println("il cambio è stato fatto con -> " + sostituito);

        System.out.println("\n===ForEach===");

        for (Product p : products){
            System.out.printf(" [%d] %-25s %-8s %8.2f€%n",
                     p.getId(), p.getName(), p.getCategory(), p.getPrice());
        }

        System.out.println("Totale prodotti nel catalogo: " + products.size());

        System.out.println("\n===Libri sopra i 30€===");
        List<Product> booksCari = products.stream()
                .filter(p -> "Books".equalsIgnoreCase(p.getCategory()) && p.getPrice() > 30)
                .collect(Collectors.toList());

        booksCari.forEach(p -> System.out.println("  " + p));

        booksCari.forEach(p -> System.out.println(" " + p ));

        System.out.println("\n===sconto 10%===");
        List<Product> scontoDieci = products.stream()
                .filter(p -> "Boys".equalsIgnoreCase(p.getCategory()))
                .map(p -> new Product(
                        p.getId(),
                        p.getName(),
                        p.getCategory(),
                        p.getPrice()*0.9))
                .collect(Collectors.toList());

        scontoDieci.forEach(p ->
                System.out.printf(" %-22s %8.2f€n", p.getName(), p.getPrice()));



        System.out.println("\n===ORDINI CON ALMENO UN PRODOTTO BABY===");

        List<Order> ordiniBaby = orders.stream()
                .filter(o -> o.getProducts().stream()
                        .anyMatch(p -> "Baby".equalsIgnoreCase(p.getCategory())))
                .collect(Collectors.toList());

        ordiniBaby.forEach(o -> System.out.println("  " + o));

        System.out.println("\n=== 2.4 PRODOTTI ORDINATI DA CLIENTI TIER 2 (LUG 2026) ===");

        LocalDate inizio = LocalDate.of(2026, 7, 1);
        LocalDate fine   = LocalDate.of(2026, 8, 1);

        List<Product> prodottiTier2 = orders.stream()
                .filter(o -> o.getCustomer().getTier() == 2)
                .filter(o -> !o.getOrderDate().isBefore(inizio)
                        && !o.getOrderDate().isAfter(fine))
                .flatMap(o -> o.getProducts().stream())
                .distinct()
                .collect(Collectors.toList());

        prodottiTier2.forEach(p -> System.out.printf("  %-22s %-8s %8.2f€%n",
                p.getName(), p.getCategory(), p.getPrice()));

    }
}
