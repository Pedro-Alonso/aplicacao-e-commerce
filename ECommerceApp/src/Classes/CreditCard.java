package ECommerceApp.src.Classes;

import java.time.LocalDate;

public class CreditCard extends PaymentCard{
    private double limiteCartao;
    
    public CreditCard(int numeroCartao, String nomeCompleto, LocalDate validade, int codigoSeguranca, double limiteCartao){
        super(numeroCartao, nomeCompleto, validade, codigoSeguranca);
        this.limiteCartao = limiteCartao;
    }

    
    public double getLimiteCartao() {
        return limiteCartao;
    }

    public void setLimiteCartao(double limiteCartao) {
        this.limiteCartao = limiteCartao;
    }
     
}
