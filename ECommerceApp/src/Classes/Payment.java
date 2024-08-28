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
}
