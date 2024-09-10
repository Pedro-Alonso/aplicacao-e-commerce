package ECommerceApp.src.Classes;

import java.util.ArrayList;
import java.util.UUID;

public class Cart {

    private UUID id;
    private ArrayList<Product> products;
    private double totalPrice;
    private UUID userId;


    public Cart(UUID userId) {
        this.id = UUID.randomUUID();
        this.products = new ArrayList<>();
        this.userId = userId;
        this.totalPrice = 0;
    }

    public void addProduct(Product product) {
        this.products.add(product);
        this.totalPrice += product.getPrice();
    }

    public void removeProduct(Product product) {
        this.products.remove(product);
        this.totalPrice -= product.getPrice();
    }

    public void clearCart() {
        this.products.clear();
        this.totalPrice = 0;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public UUID getId() {
        return id;
    }
    
    public UUID getUserId() {
        return userId;
    }


    public String displayCart() {
        String productDisplay = "";
        for (Product product : products) {
            productDisplay += product.getName() + ", ";
        }
        return String.format("""
                Preco Total: %s
                Produtos: %s
                """, totalPrice, productDisplay);
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    
}
