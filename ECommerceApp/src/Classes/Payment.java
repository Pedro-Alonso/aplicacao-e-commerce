package ECommerceApp.src.Classes;

import java.time.LocalDateTime;
import java.util.UUID;

public class Payment {

  private UUID paymentId;
  private double amount;
  private LocalDateTime issueDate;
  private LocalDateTime dueDate;
  private LocalDateTime paymentDate;
  private PaymentStatus paymentStatus;
  private ECommerceUser sender;
  private ECommerceUser receiver;

  public enum PaymentStatus {
    PENDING,
    PAID,
    CANCELLED,
  }

  /**
   * Constructor for the Payment class
   * @param amount The amount of the payment (in BRL) -> {@code double}
   * @param dueDate The due date of the payment -> {@link LocalDateTime}
   * @param sender The sender of the payment -> {@link ECommerceUser}
   * @param receiver The receiver of the payment -> {@link ECommerceUser}
   */
  public Payment(
    double amount,
    LocalDateTime dueDate,
    ECommerceUser sender,
    ECommerceUser receiver
  ) {
    this.paymentId = UUID.randomUUID();
    this.amount = amount;
    this.issueDate = LocalDateTime.now();
    this.dueDate = dueDate;
    this.paymentDate = null;
    this.paymentStatus = PaymentStatus.PENDING;
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
      Sender: %s
      Receiver: %s
      """,
        paymentId,
        amount,
        issueDate,
        dueDate,
        paymentDate,
        paymentStatus,
        sender,
        receiver
      )
    );
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
   * {@return the paymentId as a {@link UUID}}
   */
  public UUID getPaymentId() {
    return paymentId;
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
