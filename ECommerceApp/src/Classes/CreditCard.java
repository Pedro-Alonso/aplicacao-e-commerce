package ECommerceApp.src.Classes;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CreditCard extends PaymentCard{
    private double limiteCartao;
    
    public CreditCard(double amount, LocalDateTime dueDate, ECommerceUser sender, ECommerceUser receiver, int numeroCartao, String nomeCompleto, LocalDate validade, int codigoSeguranca, double limiteCartao){
        super(amount, dueDate, sender, receiver, numeroCartao, nomeCompleto, validade, codigoSeguranca);
        this.limiteCartao = limiteCartao;
    }

    // Verificar limite do cartão
    public boolean verificarLimite(double limiteCartao){
        if(limiteCartao <= 0){
            return false; // cartão não possui limite disponível
        }
        
        return true; // cartão possui limite
    }

    // Alterar o limitie do cartão
    public double alterarLimite(double limiteCartao, double valor){
        if(limiteCartao < 0){ // se o limite do cartão for menor que zero, não é possível deixar ele mais negativo
            return limiteCartao;
        }else{
            limiteCartao -= valor;
        }
        
        return limiteCartao;
    }

    // Juros proveniente da taxa do banco
    public double jurosBanco(double valorProduto){
        return (valorProduto*0.02); // juros do banco sbore a transação de crédito de 3%
    }



    // Getters and setters
    public double getLimiteCartao() {
        return limiteCartao;
    }

    public void setLimiteCartao(double limiteCartao) {
        this.limiteCartao = limiteCartao;
    }
     
}
