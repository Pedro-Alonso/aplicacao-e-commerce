package ECommerceApp.src.Classes;

import java.util.ArrayList;
import java.util.UUID;

public class Cart {

    private UUID id;
    private ArrayList<Product> products;
    private double totalPrice;


    public Cart() {
        this.id = UUID.randomUUID();
        this.products = new ArrayList<>();
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

    public void displayCart() {
        System.out.println("Cart ID: " + id);
        System.out.println("Total Price: " + totalPrice);
        System.out.println("Products in Cart:");
        for (Product product : products) {
            product.displayProduct();
        }
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    
}
