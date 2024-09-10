package ECommerceApp.src.Classes;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CreditCard extends PaymentCard{
    private double limiteCartao;
    
    public CreditCard(double limiteCartao, double amount, LocalDateTime dueDate, ECommerceUser sender, ECommerceUser receiver, String numeroCartao, String nomeCompleto, LocalDate validade, int codigoSeguranca, Bandeira bandeira, NivelCartao nivelCartao) {
        super(amount, dueDate, sender, receiver, numeroCartao, nomeCompleto, validade, codigoSeguranca, bandeira, nivelCartao);
        this.limiteCartao = limiteCartao;
    }

    // verificar se as quantidades de parcelas são válidas
    public boolean verificaQuantidadeParcela(int quantidadeParcelas){
        if(quantidadeParcelas <= 0 || quantidadeParcelas > 12){
            return false; // não é possível
        }

        return true;
    }

    // Verificar limite do cartão
    public boolean verificarLimite(double limiteCartao){
        if(limiteCartao <= 0 || super.getAmount() > limiteCartao){
            return false; // cartão não possui limite disponível ou o valor do produto é maior que o limite disponível
        }
        
        return true; // cartão possui limite
    }

    // Alterar o limitie do cartão
    public double alterarLimite(double limiteCartao){
        if(limiteCartao < 0){ // se o limite do cartão for menor que zero, não é possível deixar ele mais negativo
            return limiteCartao;
        }else{
            limiteCartao -= super.getAmount();
        }
        
        return limiteCartao;
    }

    // Juros proveniente da taxa do banco
    public double jurosBanco(){
        return (super.getAmount()*0.02); // juros do banco sbore a transação de crédito de 2%
    }

    // possibilidade de parcelamento
    public double valorParcelamento(int quantidadeParcelas){
        if(verificarLimite(limiteCartao) == false || verificaQuantidadeParcela(quantidadeParcelas) == false){
            return 0; // cartão não possui limite disponível ou as parcelas são inválidas
        }
        
        // uma só parcela não possui juros
        if(quantidadeParcelas == 1){
            return super.getAmount();
        }

        double valorParcela = (super.getAmount()/quantidadeParcelas)*jurosBanco(); // o valor total já está no super
        
        return valorParcela;
        
    }

    public void exibirParcelas(int quantidadeParcelas){
        if(verificaQuantidadeParcela(quantidadeParcelas) == false){
            return;
        }
        
        for(int i = 0; i < quantidadeParcelas; i++){
            System.out.println("Data inicial: " + super.getPaymentDate().plusMonths(i+1));
            System.out.println("Parcela: " + (i+1));
            System.out.println("Valor parcela: R$" + valorParcelamento(quantidadeParcelas));
            System.out.println("======================");
        }

        return;
    }


    // Getters and setters
    public double getLimiteCartao() {
        return limiteCartao;
    }

    public void setLimiteCartao(double limiteCartao) {
        this.limiteCartao = limiteCartao;
    }
     
}
