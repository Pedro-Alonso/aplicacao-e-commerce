package ECommerceApp.src.Classes;

import java.time.LocalDateTime;
import java.util.UUID;

public class Order {

  private UUID id;
  private LocalDateTime createdAt; 
  private LocalDateTime updatedAt;
  private OrderStatus status;
  private BillingAddress billingAddress;
  private Cart cart;
  private PaymentStatus paymentStatus;
  private PaymentMethod paymentMethod;


  public static enum OrderStatus {
    PENDING,
    CONFIRMED,
    PAYED
  }
  
  public static enum PaymentStatus {
    PENDING,
    COMPLETED,
    FAILED,
    REFUNDED
  }

  public static enum PaymentMethod {
    CREDIT_CARD,
    DEBIT_CARD,
    BANK_TRANSFER
  }

  /**
   * Constructor for Order class
   * @param billingAddress The delivery address ID -> {@link UUID}
   * @param cart The cart containing the products -> {@link Cart}
   */

  public Order(BillingAddress billingAddress, Cart cart, PaymentMethod paymentMethod) {
    this.id = UUID.randomUUID();
    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();
    this.status = OrderStatus.PENDING;
    this.billingAddress = billingAddress;
    this.cart = cart;
    this.paymentMethod = paymentMethod;
    this.paymentStatus = PaymentStatus.PENDING;
  }

  /**
   * {@return the attributes of the Order object as a {@link String}}
   */
  public String displayOrder() {
    return (
      String.format(
        """
                Order ID: %s
                Created at: %s
                Updated at: %s
                Status: %s
                Billing Address ID: %s
                Total Amount: %s
                Product IDs: %s
                """,
        this.id,
        this.createdAt,
        this.updatedAt,
        this.status,
        this.billingAddress,
        this.cart.getTotalPrice(),
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
  public Adress billingAddress() {
    return this.billingAddress;
  }

}
