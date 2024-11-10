package ECommerceApp.src.Classes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

// removed userId, since order will be stored in the user
// Changed delivery adress to billing address
// Simplified the possible statuses
// Removed array of product IDs and totalAmount, since both are already int the cart
// Deleted a most of the code, since it made no fucking sense

public class Order {

  private UUID id;
  private LocalDateTime createdAt; 
  private LocalDateTime updatedAt;
  private OrderStatus status;
  private Address billingAddress;
  private Cart cart;

  public static enum OrderStatus {
    PENDING,
    CONFIRMED,
  }


  /**
   * Constructor for Order class
   * @param billingAddress The delivery address ID -> {@link UUID}
   * @param cart The cart containing the products -> {@link Cart}
   */

  public Order(UUID billingAddress, Cart cart) {
    this.id = UUID.randomUUID();
    this.createdAt = LocalDateTime.now();
    this.updatedAt = null;
    this.status = OrderStatus.DRAFT;
    this.billingAddress = billingAddress;
    this.cart = cart;
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
