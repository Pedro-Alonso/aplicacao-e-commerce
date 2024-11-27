package ECommerceApp.src.Classes;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CreditCard extends PaymentCard{
    private double cardLimit;
    
    public CreditCard(double cardLimit, double amount, LocalDateTime dueDate, ECommerceUser sender, ECommerceUser receiver, String cardNumber, String fullName, LocalDate expirationDate, int securityCode, CardBrand cardBrand, CardLevel cardLevel) {
        super(orderId, amount, dueDate, paymentType, sender, receiver, cardNumber, fullName, expirationDate, securityCode, cardBrand, cardLevel);
        this.cardLimit = cardLimit;
    }

    // verificar se as quantidades de parcelas são válidas
    public boolean validateInstallmentQuantity(int installments){
        if(installments <= 0 || installments > 12){
            return false; // não é possível
        }

        return true;
    }

    // Verificar limite do cartão
    public boolean verifyLimit(double cardLimit){
        if(cardLimit <= 0 || super.getAmount() > cardLimit){
            return false; // cartão não possui limite disponível ou o valor do produto é maior que o limite disponível
        }
        
        return true; // cartão possui limite
    }

    // Alterar o limitie do cartão
    public double updateLimit(double cardLimit){
        if(cardLimit < 0){ // se o limite do cartão for menor que zero, não é possível deixar ele mais negativo
            return cardLimit;
        }else{
            cardLimit -= super.getAmount();
        }
        
        return cardLimit;
    }

    // Juros proveniente da taxa do banco
    public double bankFees(){
        return (super.getAmount()*0.02); // juros do banco sbore a transação de crédito de 2%
    }

    // possibilidade de parcelamento
    public double installmentAmount(int installments){
        if(verifyLimit(cardLimit) == false || validateInstallmentQuantity(installments) == false){
            return 0; // cartão não possui limite disponível ou as parcelas são inválidas
        }
        
        // uma só parcela não possui juros
        if(installments == 1){
            return super.getAmount();
        }

        double installmentValue = (super.getAmount()/installments)*bankFees(); // o valor total já está no super
        
        return installmentValue;
        
    }


    // função para testar
    public void displayInstallments(int installments){
        if(validateInstallmentQuantity(installments) == false){
            return;
        }
        
        for(int i = 0; i < installments; i++){
            System.out.println("Data inicial: " + super.getPaymentDate().plusMonths(i+1));
            System.out.println("Parcela: " + (i+1));
            System.out.println("Valor parcela: R$" + installmentAmount(installments));
            System.out.println("======================");
        }

        return;
    }


    // Getters and setters
    public double getCardLimit() {
        return cardLimit;
    }

    public void setCardLimit(double cardLimit) {
        this.cardLimit = cardLimit;
    }
     
}
