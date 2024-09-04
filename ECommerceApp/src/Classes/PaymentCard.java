package ECommerceApp.src.Classes;

import java.time.LocalDate;

public class PaymentCard extends Payment { 
    private int numeroCartao;
    // private String bandeira;
    private String nomeCompleto;
    private LocalDate validade;
    private int codigoSeguranca;
    
    private enum bandeira{
        MASTERCARD,
        VISA, 
        ELO, 
        AMERICAN_EXPRESS,
        HIPERCARD
    } 
    
    private enum nivelCartao{
        GOLD,
        PLATINUM,
        BLACK
    }
    
    public PaymentCard(int numeroCartao, String nomeCompleto, LocalDate validade, int codigoSeguranca){
        this.numeroCartao = numeroCartao;
        this.nomeCompleto = nomeCompleto;
        this.validade = validade;
        this.codigoSeguranca = codigoSeguranca;
    }
    
    
    
    // Getters and Setters
    public int getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(int numeroCartao) {
        this.numeroCartao = numeroCartao;
    }


    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    public int getCodigoSeguranca() {
        return codigoSeguranca;
    }

    public void setCodigoSeguranca(int codigoSeguranca) {
        this.codigoSeguranca = codigoSeguranca;
    }
   
}
