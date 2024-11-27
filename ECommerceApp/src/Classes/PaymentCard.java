package ECommerceApp.src.Classes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

public class PaymentCard extends Payment{ 
    private String cardNumber;
    private String fullName;
    private LocalDate expirationDate;
    private int securityCode;
    private CardBrand cardBrand;
    private CardLevel cardLevel;
    
    protected enum CardBrand{
        MASTERCARD,
        VISA, 
        ELO, 
        AMERICAN_EXPRESS,
        HIPERCARD
    } 
    
    protected enum CardLevel{
        NORMAL,
        GOLD,
        PLATINUM,
        BLACK
    }
    
    public PaymentCard(UUID orderId, double amount, LocalDateTime dueDate, PaymentType paymentType, ECommerceUser sender, ECommerceUser receiver, String cardNumber, String fullName, LocalDate expirationDate, int securityCode, CardBrand cardBrand, CardLevel cardLevel){ // extends Payment
        super(orderId, amount, dueDate, paymentType, sender, receiver);
        this.cardNumber = CardInfoFactory.generateCardNumber();
        this.fullName = fullName;
        this.expirationDate = CardInfoFactory.generateExpirationDate();
        this.securityCode = CardInfoFactory.generateSecurityCode();
        this.cardBrand = cardBrand;
        this.cardLevel = cardLevel;
    }
    
    
    
    // Getters and Setters
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(int securityCode) {
        this.securityCode = securityCode;
    }

    public CardBrand getBrand() {
        return cardBrand;
    }

    public void setBrand(CardBrand cardBrand) {
        this.cardBrand = cardBrand;
    }

    public CardLevel getCardLevel() {
        return cardLevel;
    }

    public void setCardLevel(CardLevel cardLevel) {
        this.cardLevel = cardLevel;
    }

    
}