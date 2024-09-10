package ECommerceApp.src.Classes;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class PaymentSlip extends Payment {
    private final double fineRate;
    private final double feeRatePerDay;
    private static final double MAX_FINE_RATE = 0.02;
    private static final double MAX_FEE_RATE_PER_DAY = 0.00033;
    // declared the maximum fee rate per day and the maximum fine rate the brazilian
    // legislation allows

    /**
     * Constructs a PaymentSlip object.
     *
     * @param orderId       the order ID related to the payment
     * @param amount        the amount of the payment
     * @param dueDate       the due date of the payment
     * @param paymentType   the payment type
     * @param sender        the sender of the payment
     * @param receiver      the receiver of the payment
     * @param fineRate      the fine rate for late payment (up to 2%)
     * @param feeRatePerDay the fee rate per day for late payment (up to 0.033%)
     */
    public PaymentSlip(
        UUID orderId,
        double amount,
        LocalDateTime dueDate,
        PaymentType paymentType,
        ECommerceUser sender,
        ECommerceUser receiver,
        double fineRate,
        double feeRatePerDay) {
        super(orderId, amount, dueDate, paymentType, sender, receiver);

        // validate whether the fine rate is legal or not
        if (fineRate > MAX_FINE_RATE) {
            throw new IllegalArgumentException("The fine rate cannot be higher than " + MAX_FINE_RATE);
        }

        // validate whether the fee rate is legal or not
        if (feeRatePerDay > MAX_FEE_RATE_PER_DAY) {
            throw new IllegalArgumentException("The fee rate cannot be higher than " + MAX_FEE_RATE_PER_DAY);
        }

        this.fineRate = fineRate;
        this.feeRatePerDay = feeRatePerDay;
    }

    /**
     * 
     * @return a receipt for the payment slip as a {@link String} object
     */
    @Override
    public String generateReceipt() {
        if (fineRate > MAX_FINE_RATE) {
            throw new IllegalArgumentException("The fine rate cannot be higher than " + MAX_FINE_RATE);
        }

        return (super.generateReceipt() +
                "\nFine Rate: " + fineRate +
                "\nFine Value: " + getFineValue() + 
                "\nFee Rate Per Day: " + feeRatePerDay +
                "\nDelay Fee Value: " + getDelayFeeValue() +
                "\nFinal Value: " + getFinalValue());
    }

    /**
     * Calculates the number of days the payment has been delayed.
     *
     * @return the number of days of delay
     */
    private long getDaysOfFee() {
        LocalDateTime dueDate = getDueDate();
        
        return this.getPaymentStatus() == PaymentStatus.PAID ? dueDate.until(this.getPaymentDate(), ChronoUnit.DAYS) : dueDate.until(LocalDateTime.now(), ChronoUnit.DAYS);
    }

    /**
     * Calculates the fine value for the payment slip.
     *
     * @return the fine value
     */
    public double getFineValue() {
        if (this.getPaymentStatus() == PaymentStatus.CANCELLED) {
            return 0;
        }

        if (this.getDaysOfFee() < 0) {
            return 0;
        }

        double amount = getAmount();
        return amount * this.fineRate;
    }

    /**
     * Calculates the delay fee value for the payment slip.
     *
     * @return the delay fee value
     */
    public double getDelayFeeValue() {
        PaymentStatus paymentStatus = getPaymentStatus();

        // if the payment has already been canceled, it doesn't have any fee 
        if (paymentStatus == PaymentStatus.CANCELLED) {
            return 0;
        }

        // if the payment has already been paid, the fee is calculated from the payment date, but, if it's pending, from the current date
        long daysOfFee = this.getDaysOfFee();

        // validate whether the payment is delayed or not
        if (daysOfFee < 0) {
            // not delayed
            return 0;
        }

        // calculate compound interest to get the total fine
        double initialAmount = getAmount();
        return initialAmount * Math.pow((1 + this.feeRatePerDay), daysOfFee) - initialAmount;
    }

    /**
     * Calculates the final value of the payment slip, including fines and fees.
     *
     * @return the final value
     */
    public double getFinalValue() {
        return getAmount() + getFineValue() + getDelayFeeValue();
    }

    /**
     * Gets the fine rate for the payment slip.
     *
     * @return the fine rate
     */
    public double getFineRate() {
        return fineRate;
    }

    /**
     * Gets the fee rate per day for the payment slip.
     *
     * @return the fee rate per day
     */
    public double getFeeRatePerDay() {
        return feeRatePerDay;
    }
}