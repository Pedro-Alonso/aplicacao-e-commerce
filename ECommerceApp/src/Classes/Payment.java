package ECommerceApp.src.Classes;

import java.time.LocalDateTime;
import java.util.UUID;

public class Payment {
    protected UUID paymentId;
    protected double amount;
    protected LocalDateTime issueDate;
    protected LocalDateTime dueDate;
    protected LocalDateTime paymentDate;
    protected Status paymentStatus;
    protected ECommerceUser sender;
    protected ECommerceUser receiver;

    public enum Status {
        PENDING,
        PAID,
        CANCELLED
    }

    public UUID getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(UUID paymentId) {
        this.paymentId = paymentId;
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
