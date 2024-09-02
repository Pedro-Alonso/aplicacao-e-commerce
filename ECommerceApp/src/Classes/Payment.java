package ECommerceApp.src.Classes;

import java.time.LocalDateTime;
import java.util.UUID;

public class Payment {

  private UUID paymentId;
  private double amount;
  private LocalDateTime issueDate;
  private LocalDateTime dueDate;
  private LocalDateTime paymentDate;
  private Status paymentStatus;
  private ECommerceUser sender;
  private ECommerceUser receiver;

  public enum Status {
    PENDING,
    PAID,
    CANCELLED,
  }

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
    this.paymentStatus = Status.PENDING;
    this.sender = sender;
    this.receiver = receiver;
  }

  /**
   * Method that returns the attributes of the Payment object
   * @return void
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
   * Method to set the Payment as PAID
   * @return void
   */
  public void pay() {
    this.paymentDate = LocalDateTime.now();
    this.paymentStatus = Status.PAID;
  }

  /**
   * Method to cancel the Payment
   * @return void
   */
  public void cancel() {
    this.paymentDate = null;
    this.paymentStatus = Status.CANCELLED;
  }

  public UUID getPaymentId() {
    return paymentId;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public LocalDateTime getIssueDate() {
    return issueDate;
  }

  public void setIssueDate(LocalDateTime issueDate) {
    this.issueDate = issueDate;
  }

  public LocalDateTime getDueDate() {
    return dueDate;
  }

  public void setDueDate(LocalDateTime dueDate) {
    this.dueDate = dueDate;
  }

  public LocalDateTime getPaymentDate() {
    return paymentDate;
  }

  public void setPaymentDate(LocalDateTime paymentDate) {
    this.paymentDate = paymentDate;
  }

  public Status getPaymentStatus() {
    return paymentStatus;
  }

  public void setPaymentStatus(Status paymentStatus) {
    this.paymentStatus = paymentStatus;
  }

  public ECommerceUser getSender() {
    return sender;
  }

  public void setSender(ECommerceUser sender) {
    this.sender = sender;
  }

  public ECommerceUser getReceiver() {
    return receiver;
  }

  public void setReceiver(ECommerceUser receiver) {
    this.receiver = receiver;
  }
}
