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
    CANCELLED
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

  // ORDER PROCESSING METHODS
  public void confirmOrder() {
    if (this.status == OrderStatus.PENDING && this.paymentStatus == PaymentStatus.COMPLETED) {
        this.status = OrderStatus.CONFIRMED;
        this.updateTimestamp();
    } else {
        throw new IllegalStateException("Order cannot be confirmed. Check payment status.");
    }
  }

  public void cancelOrder() {
    if (this.status != OrderStatus.CONFIRMED) {
        this.status = OrderStatus.CANCELLED;
        this.updateTimestamp();
    } else {
        throw new IllegalStateException("Cannot cancel order after confirmation.");
    }
  }




}
