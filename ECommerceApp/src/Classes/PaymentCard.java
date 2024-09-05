package ECommerceApp.src.Classes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random; 

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
    
    public PaymentCard(double amount, LocalDateTime dueDate, ECommerceUser sender, ECommerceUser receiver, int numeroCartao, String nomeCompleto, LocalDate validade, int codigoSeguranca){
        super(amount, dueDate, sender, receiver);
        this.numeroCartao = numeroCartao;
        this.nomeCompleto = nomeCompleto;
        this.validade = gerarValidade();
        this.codigoSeguranca = gerarCodigoSeguranca();
    }
    
    // gerar validade aleatória 
    public LocalDate gerarValidade(){
        Random random = new Random();
        LocalDate validadeAleatoria = LocalDate.now();
        int dias = 1 + random.nextInt(28); // escolhe um dia entre 1 e 27  
        int mes = 1 + random.nextInt(11); // escolhe um mês entre 1 e 12
        int ano = random.nextInt(10); // escolhe um ano entre 0 a 10 anos pra frente
        
        validadeAleatoria = validadeAleatoria.plusDays(dias)
                                             .plusMonths(mes)
                                             .plusYears(ano);

        return validadeAleatoria;
    }

    // gerar codigo de segurança aleatório
    public int gerarCodigoSeguranca(){
        Random codigoAleatorio = new Random();
        return 100 + codigoAleatorio.nextInt(900); // vai gerar um número entre 100 e 999;
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
