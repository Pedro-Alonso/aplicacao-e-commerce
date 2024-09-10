package ECommerceApp.src.Classes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

public class PaymentCard extends Payment{ 
    private String numeroCartao;
    private String nomeCompleto;
    private LocalDate validade;
    private int codigoSeguranca;
    private Bandeira bandeira;
    private NivelCartao nivelCartao;
    
    protected enum Bandeira{
        MASTERCARD,
        VISA, 
        ELO, 
        AMERICAN_EXPRESS,
        HIPERCARD
    } 
    
    protected enum NivelCartao{
        NORMAL,
        GOLD,
        PLATINUM,
        BLACK
    }
    
    public PaymentCard(double amount, LocalDateTime dueDate, ECommerceUser sender, ECommerceUser receiver, String numeroCartao, String nomeCompleto, LocalDate validade, int codigoSeguranca, Bandeira bandeira, NivelCartao nivelCartao){ // extends Payment
        super(amount, dueDate, sender, receiver);
        this.numeroCartao = gerarNumeroCartao();
        this.nomeCompleto = nomeCompleto;
        this.validade = gerarValidade();
        this.codigoSeguranca = gerarCodigoSeguranca();
        this.bandeira = bandeira;
        this.nivelCartao = nivelCartao;
    }
    
    
    // gerar os 16 números do cartão - Algoritmo de Luhn
    private String gerarNumeroCartao(){
        Random random = new Random();
        int cartao[] = new int[16];

        // Gerar 15 números aleatórios
        for(int i = 0; i < 15; i++){
            cartao[i] = random.nextInt(10); // numeros de 0 a 9
        }

        // Último algarismo é o verificador segundo o algoritmo
        cartao[15] = LuhnTeste(cartao);
        
        // Converter o array de números em string (fica mais fácil trabalhar depois)
        StringBuilder NumeroCartaoGerado = new StringBuilder();
        
        // Usar um for each para cada elemento do array
        for(int i = 0; i < cartao.length; i++){
            NumeroCartaoGerado.append(cartao[i]);
        }
        
        return NumeroCartaoGerado.toString();
    }
    
    private int LuhnTeste(int cartao[]){
        // inverter os numeros 
        // somar os números na posiçõa ímpar (s1)
        // multiplicar por 2 os números na posição impar
        // soma os algoritmos da soma separadamente (16 -> 1+6=7) ou se ele for > 9, subtrair 9
        // somar os algoritmos juntos (s2)
        // se s1+s2 tem como último digito 0, ele é válido
        
        int j = 0, s1 = 0, s2 = 0;
        int numero[] = new int[16];
        
        for(int i = cartao.length - 1; i >= 0; i--){ // inverter os números
            numero[j] = cartao[i];
            j++;
        }
        
        j = 0;
        
        for(int i = 0; i < numero.length - 1; i++){ 
            if(i % 2 != 0){ // se for impar soma
                s1 += numero[i];
            }else{ // se for par multiplica por dois
                numero[i] *= 2;
                if(numero[i] > 9){ // se maior que 9, subtrai
                    numero[i] -= 9;
                }
                s2 += numero[i];
            }
        }
        
        return (10 - ((s1+s2) % 10)) % 10; // retorna um digíto que possui o último número soma
        
    } 
    
    // gerar validade aleatória 
    private LocalDate gerarValidade(){
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
    private int gerarCodigoSeguranca(){
        Random codigoAleatorio = new Random();
        return 100 + codigoAleatorio.nextInt(900); // vai gerar um número entre 100 e 999;
    }
    
    
    // Getters and Setters
    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
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

    public Bandeira getBandeira() {
        return bandeira;
    }

    public void setBandeira(Bandeira bandeira) {
        this.bandeira = bandeira;
    }

    public NivelCartao getNivelCartao() {
        return nivelCartao;
    }

    public void setNivelCartao(NivelCartao nivelCartao) {
        this.nivelCartao = nivelCartao;
    }

    
}