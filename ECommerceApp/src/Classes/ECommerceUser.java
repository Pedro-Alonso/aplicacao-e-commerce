package ECommerceApp.src.Classes;

import java.util.ArrayList;

public class ECommerceUser extends User {

  private ArrayList<Product> cart;
  private ArrayList<Order> orders;
  private ArrayList<ProductReview> productReviews;
  private ArrayList<DeliveryAddress> deliveryAddresses;

  public ECommerceUser(String username, String email, String password) {
    super(username, email, password);
    this.cart = new ArrayList<>();
    this.orders = new ArrayList<>();
    this.productReviews = new ArrayList<>();
    this.deliveryAddresses = new ArrayList<>();
  }

  // Adiciona um produto ao carrinho
  public void addToCart(Product product) {
    this.cart.add(product);
  }

  // Remove um produto do carrinho
  public void removeFromCart(Product product) {
    this.cart.remove(product);
  }

  // Faz um pedido com base no carrinho atual
  public void placeOrder() {
    Order newOrder = new Order(this.cart, this);
    this.orders.add(newOrder);
    this.cart.clear(); // Limpa o carrinho após o pedido
  }

  // Adiciona uma avaliação para um produto
  public void addProductReview(ProductReview review) {
    this.productReviews.add(review);
  }

  // Adiciona um endereço de entrega
  public void addDeliveryAddress(DeliveryAddress address) {
    this.deliveryAddresses.add(address);
  }

  // Remove um endereço de entrega
  public void removeDeliveryAddress(DeliveryAddress address) {
    this.deliveryAddresses.remove(address);
  }

  // Getters e setters para os atributos
  public ArrayList<Product> getCart() {
    return cart;
  }

  public void setCart(ArrayList<Product> cart) {
    this.cart = cart;
  }

  public ArrayList<Order> getOrders() {
    return orders;
  }

  public void setOrders(ArrayList<Order> orders) {
    this.orders = orders;
  }

  public ArrayList<ProductReview> getProductReviews() {
    return productReviews;
  }

  public void setProductReviews(ArrayList<ProductReview> productReviews) {
    this.productReviews = productReviews;
  }

  public ArrayList<DeliveryAddress> getDeliveryAddresses() {
    return deliveryAddresses;
  }

  public void setDeliveryAddresses(
    ArrayList<DeliveryAddress> deliveryAddresses
  ) {
    this.deliveryAddresses = deliveryAddresses;
  }
}
