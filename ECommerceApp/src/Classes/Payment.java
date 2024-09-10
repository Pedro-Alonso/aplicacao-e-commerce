package ECommerceApp.src.Classes;

import java.time.LocalDateTime;
import java.util.UUID;

public class Payment {

  private UUID id;
  private UUID orderId;
  private double amount;
  private LocalDateTime issueDate;
  private LocalDateTime dueDate;
  private LocalDateTime paymentDate;
  private PaymentStatus paymentStatus;
  private PaymentType paymentType;
  private ECommerceUser sender;
  private ECommerceUser receiver;

  public static enum PaymentStatus {
    PENDING,
    PAID,
    CANCELLED,
  }

  public static enum PaymentType {
    CREDIT_CARD,
    DEBIT_CARD,
    DIGITAL_CARD,
    BOLETO,
  }

  /**
   * Constructor for the Payment class
   * @param orderId The order ID related to the payment -> {@link UUID}
   * @param amount The amount of the payment (in BRL) -> {@code double}
   * @param dueDate The due date of the payment -> {@link LocalDateTime}
   * @param paymentType The payment type -> {@link PaymentType}
   * @param sender The sender of the payment -> {@link ECommerceUser}
   * @param receiver The receiver of the payment -> {@link ECommerceUser}
   */
  public Payment(
    UUID orderId,
    double amount,
    LocalDateTime dueDate,
    PaymentType paymentType,
    ECommerceUser sender,
    ECommerceUser receiver
  ) {
    this.id = UUID.randomUUID();
    this.orderId = orderId;
    this.amount = amount;
    this.issueDate = LocalDateTime.now();
    this.dueDate = dueDate;
    this.paymentDate = null;
    this.paymentStatus = PaymentStatus.PENDING;
    this.paymentType = paymentType;
    this.sender = sender;
    this.receiver = receiver;
  }

  /**
   * {@return the attributes of the Payment object as a {@link String}}
   */
  public String generateReceipt() {
    return (
      String.format(
        """
      Payment ID: %s
      Amount: %s
      Issue date: %s
      Due date: %s
      Payment date: %s
      Payment status: %s
      Payment type: %s
      Sender: %s
      Receiver: %s
      """,
        id,
        amount,
        issueDate,
        dueDate,
        paymentDate,
        paymentStatus,
        paymentType,
        sender,
        receiver
      )
    );
  }

  /**
   * Method to get target Order ID
   * {@return the orderId as a {@link UUID}}
   */
  public UUID getOrderId() {
    return orderId;
  }

  /**
   * Method to get the Payment Type
   * {@return the paymentType as a {@link PaymentType}}
   */
  public PaymentType getPaymentType() {
    return paymentType;
  }

  /**
   * Method to set the Payment as {@link PaymentStatus#PAID}
   */
  public void pay() {
    this.paymentDate = LocalDateTime.now();
    this.paymentStatus = PaymentStatus.PAID;
  }

  /**
   * Method to set the Payment as {@link PaymentStatus#CANCELLED}
   */
  public void cancel() {
    this.paymentDate = null;
    this.paymentStatus = PaymentStatus.CANCELLED;
  }

  /**
   * {@return the id as a {@link UUID}}
   */
  public UUID getPaymentId() {
    return id;
  }

  /**
   * {@return the amount of the payment (in BRL) as a {@code double}}
   */
  public double getAmount() {
    return amount;
  }

  /**
   * Setter for the amount attribute
   * @param amount The amount of the payment (in BRL) -> {@code double}
   */
  public void setAmount(double amount) {
    this.amount = amount;
  }

  /**
   * {@return the issueDate as a {@link LocalDateTime}}
   */
  public LocalDateTime getIssueDate() {
    return issueDate;
  }

  /**
   * Setter for the issueDate attribute
   * @param issueDate The issue date of the payment -> {@link LocalDateTime}
   */
  public void setIssueDate(LocalDateTime issueDate) {
    this.issueDate = issueDate;
  }

  /**
   * {@return the dueDate as a {@link LocalDateTime}}
   */
  public LocalDateTime getDueDate() {
    return dueDate;
  }

  /**
   * Setter for the dueDate attribute
   * @param dueDate The due date of the payment -> {@link LocalDateTime}
   */
  public void setDueDate(LocalDateTime dueDate) {
    this.dueDate = dueDate;
  }

  /**
   * {@return the paymentDate as a {@link LocalDateTime}}
   */
  public LocalDateTime getPaymentDate() {
    return paymentDate;
  }

  /**
   * Setter for the paymentDate attribute
   * @param paymentDate The payment date of the payment -> {@link LocalDateTime}
   */
  public void setPaymentDate(LocalDateTime paymentDate) {
    this.paymentDate = paymentDate;
  }

  /**
   * {@return the paymentStatus as a {@link PaymentStatus}}
   */
  public PaymentStatus getPaymentStatus() {
    return paymentStatus;
  }

  /**
   * Setter for the paymentStatus attribute
   * @param paymentStatus The target PaymentStatus to set -> {@link PaymentStatus}
   */
  public void setPaymentStatus(PaymentStatus paymentStatus) {
    this.paymentStatus = paymentStatus;
  }

  /**
   * {@return the sender as a {@link ECommerceUser}}
   */
  public ECommerceUser getSender() {
    return sender;
  }

  /**
   * Setter for the sender attribute
   * @param sender The sender of the payment -> {@link ECommerceUser}
   */
  public void setSender(ECommerceUser sender) {
    this.sender = sender;
  }

  /**
   * {@return the receiver as a {@link ECommerceUser}}
   */
  public ECommerceUser getReceiver() {
    return receiver;
  }

  /**
   * Setter for the receiver attribute
   * @param receiver The receiver of the payment -> {@link ECommerceUser}
   */
  public void setReceiver(ECommerceUser receiver) {
    this.receiver = receiver;
  }
}
