package ECommerceApp.src.Classes;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class DebitCard extends PaymentCard{
    private double limiteEspecial;
    
    public DebitCard(double limiteEspecial, double amount, LocalDateTime dueDate, ECommerceUser sender, ECommerceUser receiver, int numeroCartao, String nomeCompleto, LocalDate validade, int codigoSeguranca, Bandeira bandeira, NivelCartao nivelCartao) {
        super(amount, dueDate, sender, receiver, numeroCartao, nomeCompleto, validade, codigoSeguranca, bandeira, nivelCartao);
        this.limiteEspecial = limiteEspecial;
    }
    
    
    // getters and setters
    public double getLimiteEspecial() {
        return limiteEspecial;
    }

    public void setLimiteEspecial(double limiteEspecial) {
        this.limiteEspecial = limiteEspecial;
    }
    
}
