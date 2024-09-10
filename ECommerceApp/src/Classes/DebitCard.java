package ECommerceApp.src.Classes;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class DebitCard extends PaymentCard{
    private double limiteEspecial;
    private double saldo;
    
    public DebitCard(double saldo, double limiteEspecial, double amount, LocalDateTime dueDate, ECommerceUser sender, ECommerceUser receiver, String numeroCartao, String nomeCompleto, LocalDate validade, int codigoSeguranca, Bandeira bandeira, NivelCartao nivelCartao) {
        super(amount, dueDate, sender, receiver, numeroCartao, nomeCompleto, validade, codigoSeguranca, bandeira, nivelCartao);
        this.saldo = saldo;
        this.limiteEspecial = limiteEspecial;
    }
    
    
    // verifica se a conta pode comprar
    public boolean verificaCompra(){
        double saldoConta = getSaldo();
        double limiteEspecialConta = getLimiteEspecial();
        
        if(saldoConta + limiteEspecialConta < 0){ // a conta não possui saldo e limite especial suficiente
            return false;
        }
        
        return true;
    }
    
    // altera saldo da conta
    public double alteraSaldo(){
        double valorProduto = super.getAmount();
        
        if(verificaCompra() == false){ // não possui saldo suficiente para comprar
            return 0;
        }
        
        if(saldo > valorProduto){ // possível realizar a compra
            saldo -= valorProduto;
            return saldo;
        }
        
        if(valorProduto > saldo + limiteEspecial){ // valor muito alto
            return 0; 
        }else{
            saldo -= valorProduto;
            alteraLimiteEspecial(valorProduto);
        }
        
        return saldo;
    }
    
    // alterar limite especial da conta
    public double alteraLimiteEspecial(double valor){
        if(valor < 0){
            return limiteEspecial;
        }
        
        limiteEspecial -= valor;
        return limiteEspecial;
    }
    
    
    // getters and setters
    public double getLimiteEspecial() {
        switch (super.getNivelCartao()) {
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

    public void setLimiteEspecial(double limiteEspecial) {
        this.limiteEspecial = limiteEspecial;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    
    
}
