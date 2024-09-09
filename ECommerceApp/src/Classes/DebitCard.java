package ECommerceApp.src.Classes;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class DebitCard extends PaymentCard{
    private double limiteEspecial;
    private double saldo;
    
    public DebitCard(double limiteEspecial, double amount, LocalDateTime dueDate, ECommerceUser sender, ECommerceUser receiver, int numeroCartao, String nomeCompleto, LocalDate validade, int codigoSeguranca, Bandeira bandeira, NivelCartao nivelCartao) {
        super(amount, dueDate, sender, receiver, numeroCartao, nomeCompleto, validade, codigoSeguranca, bandeira, nivelCartao);
        this.limiteEspecial = limiteEspecial;
    }
    
    // verifica se a conta possui saldo
    public boolean verificaSaldo(){
        if(saldo < 0){
            return false;
        }
        
        return true;
    }


    // alterar saldo da conta
    public double alteraSaldo(){
        switch(super.getNivelCartao()){
            case NORMAL:
                if(limiteEspecial < 0){
                    return 0;
                }else{
                    limiteEspecial -= super.getAmount();
                }
                return limiteEspecial;
            case GOLD:
                if(limiteEspecial < -1000){
                    return 0;
                }else{
                    limiteEspecial -= super.getAmount();
                }
                return limiteEspecial;
            case PLATINUM:
                if(limiteEspecial < -2500){
                    return 0;
                }else{
                    limiteEspecial -= super.getAmount();
                }
                return limiteEspecial;
            case BLACK:
                if(limiteEspecial < -5000){
                    return 0;
                }else{
                    limiteEspecial -= super.getAmount();
                }
                return limiteEspecial;
            default:
                return 0;
        }
    }

    // getters and setters
    public double getLimiteEspecial() {
        return limiteEspecial;
    }

    public void setLimiteEspecial(double limiteEspecial) {
        this.limiteEspecial = limiteEspecial;
    }
    
}
