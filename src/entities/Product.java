package entities;

public class Product {
    private final Long id;
    private final String name;
    private final String category;
    private final double price;

    public Product(Long id, String name, String category, double price){
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString(){
        // %d = intero, %s = stringa, %.2f = decimale con 2 cifre
        return String.format("Product{id=%d, name=%s, category=%s, price=%.2f}", id, name, category, price);
    }

}
