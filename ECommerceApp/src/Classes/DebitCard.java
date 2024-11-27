package ECommerceApp.src.Classes;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class DebitCard extends PaymentCard{
    private double specialLimit;
    private double balance;
    
    public DebitCard(double balance, double specialLimit, double amount, LocalDateTime dueDate, ECommerceUser sender, ECommerceUser receiver, String cardNumber, String fullName, LocalDate expirationDate, int securityCode, CardBrand cardBrand, CardLevel cardLevel) {
        super(orderId, amount, dueDate, paymentType, sender, receiver, cardNumber, fullName, expirationDate, securityCode, cardBrand, cardLevel);
        this.balance = balance;
        this.specialLimit = getSpecialLimit();
    }
    
    
    // verifica se a conta pode comprar
    public boolean verifyPurchase(){
        double accountBalance = getBalance();
        double accountSpecialLimit = getSpecialLimit();
        
        if(accountBalance + accountSpecialLimit < 0){ // a conta não possui balance e limite especial suficiente
            return false;
        }
        
        return true;
    }
    
    // altera balance da conta
    public double updateBalance(){
        double productValue = super.getAmount();
        
        if(verifyPurchase() == false){ // não possui balance suficiente para comprar
            return 0;
        }
        
        if(balance > productValue){ // possível realizar a compra
            balance -= productValue;
            return balance;
        }
        
        if(productValue > balance + specialLimit){ // value muito alto
            return 0; 
        }else{
            balance -= productValue;
            updateSpecialLimit(productValue);
        }
        
        return balance;
    }
    
    // alterar limite especial da conta
    public double updateSpecialLimit(double value){
        if(value < 0){
            return specialLimit;
        }
        
        specialLimit -= value;
        return specialLimit;
    }
    
    
    // getters and setters
    public double getSpecialLimit() {
        switch (super.getCardLevel()) {
            case GOLD:
                return 1000;
            case PLATINUM:
                return 2500;
            case BLACK:
                return 5000;
            default:
                return 0;
        }
    }

    public void setSpecialLimit(double specialLimit) {
        this.specialLimit = specialLimit;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    
}
