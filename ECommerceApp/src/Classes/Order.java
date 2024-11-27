package ECommerceApp.src.Classes;

import java.time.LocalDateTime;
import java.util.UUID;

/**
* Represents an order in an e-commerce system, including information about the order status, payment status, and payment method.
*
* @author [Your Name]
* @version 1.0
* @since [Current Date]
*/
public class Order {

   /**
    * Unique identifier for the order.
    */
   private UUID id;

   /**
    * Timestamp of when the order was created.
    */
   private LocalDateTime createdAt;

   /**
    * Timestamp of when the order was last updated.
    */
   private LocalDateTime updatedAt;

   /**
    * Current status of the order.
    */
   private OrderStatus status;

   /**
    * Billing address for the order.
    */
   private BillingAddress billingAddress;

   /**
    * Cart containing the products in the order.
    */
   private Cart cart;

   /**
    * Current status of the payment for the order.
    */
   private PaymentStatus paymentStatus;

   /**
    * Payment method used for the order.
    */
   private PaymentMethod paymentMethod;

   /**
    * Possible statuses for an order.
    */
   public static enum OrderStatus {
       PENDING,
       CONFIRMED,
       CANCELLED
   }

   /**
    * Possible statuses for a payment.
    */
   public static enum PaymentStatus {
       PENDING,
       COMPLETED,
       FAILED,
       REFUNDED
   }

   /**
    * Possible payment methods.
    */
   public static enum PaymentMethod {
       CREDIT_CARD,
       DEBIT_CARD,
       BANK_TRANSFER
   }


   /**
    * Constructor for creating a new order.
    *
    * @param billingAddress The billing address for the order.
    * @param cart          The cart containing the products in the order.
    * @param paymentMethod The payment method used for the order.
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
    * Confirms the order, updating the status to CONFIRMED if the payment is completed.
    *
    * @throws IllegalStateException If the order status is not PENDING or the payment status is not COMPLETED.
    */
   public void confirmOrder() {
       if (this.status == OrderStatus.PENDING && this.paymentStatus == PaymentStatus.COMPLETED) {
           this.status = OrderStatus.CONFIRMED;
           this.updateTimestamp();
       } else {
           throw new IllegalStateException("Order cannot be confirmed. Check payment status.");
       }
   }

   /**
    * Cancels the order, updating the status to CANCELLED if the order has not been confirmed.
    *
    * @throws IllegalStateException If the order status is CONFIRMED.
    */
   public void cancelOrder() {
       if (this.status != OrderStatus.CONFIRMED) {
           this.status = OrderStatus.CANCELLED;
           this.updateTimestamp();
       } else {
           throw new IllegalStateException("Cannot cancel order after confirmation.");
       }
   }

   /**
    * Completes the payment for the order, updating the payment status to COMPLETED.
    */
   public void completePayment() {
       this.paymentStatus = PaymentStatus.COMPLETED;
       this.updateTimestamp();
   }

   /**
    * Marks the payment for the order as failed, updating the payment status to FAILED.
    */
   public void failPayment() {
       this.paymentStatus = PaymentStatus.FAILED;
       this.updateTimestamp();
   }

   /**
    * Refunds the payment for the order, updating the payment status to REFUNDED.
    *
    * @throws IllegalStateException If the payment status is not COMPLETED.
    */
   public void refundPayment() {
       if (this.paymentStatus == PaymentStatus.COMPLETED) {
           this.paymentStatus = PaymentStatus.REFUNDED;
           this.updateTimestamp();
       } else {
           throw new IllegalStateException("Cannot refund payment that hasn't been completed.");
       }
   }

   /**
    * Updates the timestamp of the last update to the order.
    */
   private void updateTimestamp() {
       this.updatedAt = LocalDateTime.now();
   }


    // GETTERS AND SETTERS
    public UUID getId() {
      return this.id;
  }

  public LocalDateTime getCreatedAt() {
      return this.createdAt;
  }

  public LocalDateTime getUpdatedAt() {
      return this.updatedAt;
  }

  public OrderStatus getStatus() {
      return this.status;
  }

  public BillingAddress getBillingAddress() {
      return this.billingAddress;
  }

  public Cart getCart() {
      return this.cart;
  }

  public PaymentStatus getPaymentStatus() {
      return this.paymentStatus;
  }

  public PaymentMethod getPaymentMethod() {
      return this.paymentMethod;
  }

  public double getTotalAmount() {
      return this.cart.getTotalPrice();
  }

  @Override
  public String toString() {
      return String.format("""
          Order Details:
          ID: %s
          Created: %s
          Updated: %s
          Status: %s
          Payment Status: %s
          Payment Method: %s
          Total Amount: $%.2f
          
          Billing Address:
          %s
          
          Cart Items:
          %s
          """,
          this.id,
          this.createdAt,
          this.updatedAt,
          this.status,
          this.paymentStatus,
          this.paymentMethod,
          this.getTotalAmount(),
          this.billingAddress.toString(),
          this.cart.toString()
      );
  }



}
