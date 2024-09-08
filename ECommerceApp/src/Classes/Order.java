package ECommerceApp.src.Classes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class Order {

  private UUID id;
  private UUID customerId;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private OrderStatus status;
  private UUID deliveryAddressId;
  private double totalAmount;
  private ArrayList<UUID> productIds;

  public static enum OrderStatus {
    DRAFT,
    PENDING,
    SHIPPED,
    DELIVERED,
    CANCELLED,
    FAILED,
  }

  /**
   * Constructor for Order class
   * @param customerId The customer creating the Order ID -> {@link UUID}
   * @param deliveryAddressId The delivery address ID -> {@link UUID}
   * @param totalAmount The total amount of the order -> {@code double}
   */
  public Order(UUID customerId, UUID deliveryAddressId, double totalAmount) {
    this.id = UUID.randomUUID();
    this.customerId = customerId;
    this.createdAt = LocalDateTime.now();
    this.updatedAt = null;
    this.status = OrderStatus.DRAFT;
    this.deliveryAddressId = deliveryAddressId;
    this.totalAmount = totalAmount;
    this.productIds = new ArrayList<>();
  }

  /**
   * {@return the attributes of the Order object as a {@link String}}
   */
  public String displayOrder() {
    return (
      String.format(
        """
                Order ID: %s
                Customer ID: %s
                Created at: %s
                Updated at: %s
                Status: %s
                Delivery Address ID: %s
                Total Amount: %s
                Product IDs: %s
                """,
        this.id,
        this.customerId,
        this.createdAt,
        this.updatedAt,
        this.status,
        this.deliveryAddressId,
        this.totalAmount,
        this.productIds.size()
      )
    );
  }

  /**
   * {@return the Order ID}
   */
  public UUID getId() {
    return this.id;
  }

  /**
   * {@return the Customer ID}
   */
  public UUID getCustomerId() {
    return this.customerId;
  }

  /**
   * {@return the Created At date}
   */
  public LocalDateTime getCreatedAt() {
    return this.createdAt;
  }

  /**
   * {@return the Updated At date}
   */
  public LocalDateTime getUpdatedAt() {
    return this.updatedAt;
  }

  /**
   * {@return the Order Status}
   */
  public OrderStatus getStatus() {
    return this.status;
  }

  /**
   * {@return the Delivery Address ID}
   */
  public UUID getDeliveryAddressId() {
    return this.deliveryAddressId;
  }

  /**
   * {@return the Total Amount}
   */
  public double getTotalAmount() {
    return this.totalAmount;
  }

  /**
   * {@return the Product IDs}
   */
  public ArrayList<UUID> getProductIds() {
    return this.productIds;
  }

  /**
   * Method to add a product to the Order
   * @param productId The product ID to be added to the Order -> {@link UUID}
   */
  public void addProduct(UUID productId) {
    this.productIds.add(productId);
    this.updatedAt = LocalDateTime.now();
  }

  /**
   * Method to remove a product from the Order
   * @param productId The product ID to be removed from the Order -> {@link UUID}
   * @throws Exception if the product is not found in the Order
   */
  public void removeProduct(UUID productId) {
    try {
      this.productIds.remove(productId);
    } catch (Exception e) {
      System.out.println("Produto não encontrado no pedido.");
    }
    this.updatedAt = LocalDateTime.now();
  }

  /**
   * Method to confirm the Order
   */
  public void confirm() {
    if (this.productIds.size() == 0) {
      throw new IllegalArgumentException("Pedido não possui produtos.");
    }
    this.status = OrderStatus.PENDING;
    this.updatedAt = LocalDateTime.now();
  }

  /**
   * Method to mark the Order as shipped
   * @param paymentReceipt The payment receipt for the Order -> {@link Payment}
   * @throws IllegalArgumentException if the Order is not confirmed
   * @throws IllegalArgumentException if the payment is not paid or the Order ID does not match
   */
  public void ship(Payment paymentReceipt) {
    if (this.status != OrderStatus.PENDING) {
      throw new IllegalArgumentException("Pedido não confirmado.");
    }
    if (
      paymentReceipt.getPaymentStatus() != Payment.PaymentStatus.PAID ||
      paymentReceipt.getOrderId() != this.id
    ) {
      throw new IllegalArgumentException(
        "Pagamento não efetuado ou ID do pedido não corresponde."
      );
    }
    this.status = OrderStatus.SHIPPED;
    this.updatedAt = LocalDateTime.now();
  }

  /**
   * Method to mark the Order as delivered
   * @throws IllegalArgumentException if the Order is not shipped
   */
  public void deliver() {
    if (this.status != OrderStatus.SHIPPED) {
      throw new IllegalArgumentException("Pedido não foi enviado.");
    }
    this.status = OrderStatus.DELIVERED;
    this.updatedAt = LocalDateTime.now();
  }

  /**
   * Method to cancel the Order
   * @throws IllegalArgumentException if the Order is already delivered
   */
  public void cancel() {
    if (this.status == OrderStatus.DELIVERED) {
      throw new IllegalArgumentException("Pedido já foi entregue.");
    }
    this.status = OrderStatus.CANCELLED;
    this.updatedAt = LocalDateTime.now();
  }

  /**
   * Method to mark the Order as failed
   * @throws IllegalArgumentException if the Order is already delivered
   */
  public void fail() {
    if (this.status == OrderStatus.DELIVERED) {
      throw new IllegalArgumentException("Pedido já foi entregue.");
    }
    this.status = OrderStatus.FAILED;
    this.updatedAt = LocalDateTime.now();
  }

  /**
   * Method to generate a payment receipt for the Order
   * @param paymentMethod The payment method for the Order -> {@link Payment.PaymentType}
   */
  public Payment generatePayment(
    Payment.PaymentType paymentMethod,
    LocalDateTime dueDate,
    UUID senderId,
    UUID receiverId
  ) {
    // Waiting for System class to be implemented

    /*
     * Payment payment = new Payment(
     *  this.id,
     * this.totalAmount,
     * dueDate,
     * paymentMethod,
     * senderId,
     * receiverId
     * );
     *
     * return payment;
     */
  }
}
