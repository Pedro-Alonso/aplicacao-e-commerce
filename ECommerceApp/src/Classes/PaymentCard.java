package ECommerceApp.src.Classes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

public class PaymentCard extends Payment{ 
    private String cardNumber;
    private String fullName;
    private LocalDate expirationDate;
    private int securityCode;
    private CardBrand cardBrand;
    private CardLevel cardLevel;
    
    protected enum CardBrand{
        MASTERCARD,
        VISA, 
        ELO, 
        AMERICAN_EXPRESS,
        HIPERCARD
    } 
    
    protected enum CardLevel{
        NORMAL,
        GOLD,
        PLATINUM,
        BLACK
    }
    
    public PaymentCard(double amount, LocalDateTime dueDate, ECommerceUser sender, ECommerceUser receiver, String cardNumber, String fullName, LocalDate expirationDate, int securityCode, CardBrand cardBrand, CardLevel cardLevel){ // extends Payment
        super(amount, dueDate, sender, receiver);
        this.cardNumber = generateCardNumber();
        this.fullName = fullName;
        this.expirationDate = generateExpirationDate();
        this.securityCode = generateSecurityCode();
        this.cardBrand = cardBrand;
        this.cardLevel = cardLevel;
    }
    
    
    // gerar os 16 números do cartão - Algoritmo de Luhn
    private String generateCardNumber(){
        Random random = new Random();
        int card[] = new int[16];

        // Gerar 15 números aleatórios
        for(int i = 0; i < 15; i++){
            card[i] = random.nextInt(10); // numeros de 0 a 9
        }

        // Último algarismo é o verificador segundo o algoritmo
        card[15] = LuhnAlgorithm(card);
        
        // Converter o array de números em string (fica mais fácil trabalhar depois)
        StringBuilder generatedCardNumber = new StringBuilder();
        
        // Usar um for each para cada elemento do array
        for(int i = 0; i < card.length; i++){
            generatedCardNumber.append(card[i]);
        }
        
        return generatedCardNumber.toString();
    }
    
    private int LuhnAlgorithm(int card[]){
        // inverter os numeros 
        // somar os números na posiçõa ímpar (s1)
        // multiplicar por 2 os números na posição impar
        // soma os algoritmos da soma separadamente (16 -> 1+6=7) ou se ele for > 9, subtrair 9
        // somar os algoritmos juntos (s2)
        // se s1+s2 tem como último digito 0, ele é válido
        
        int j = 0, s1 = 0, s2 = 0;
        int number[] = new int[16];
        
        for(int i = card.length - 1; i >= 0; i--){ // inverter os números
            number[j] = card[i];
            j++;
        }
        
        j = 0;
        
        for(int i = 0; i < number.length - 1; i++){ 
            if(i % 2 != 0){ // se for impar soma
                s1 += number[i];
            }else{ // se for par multiplica por dois
                number[i] *= 2;
                if(number[i] > 9){ // se maior que 9, subtrai
                    number[i] -= 9;
                }
                s2 += number[i];
            }
        }
        
        return (10 - ((s1+s2) % 10)) % 10; // retorna um digíto que possui o último número soma
        
    } 
    
    // gerar expirationDate aleatória 
    private LocalDate generateExpirationDate(){
        Random random = new Random();
        LocalDate randomExpirationDate = LocalDate.now();
        int days = 1 + random.nextInt(28); // escolhe um dia entre 1 e 27  
        int months = 1 + random.nextInt(11); // escolhe um mês entre 1 e 12
        int year = random.nextInt(10); // escolhe um year entre 0 a 10 anos pra frente
        
        randomExpirationDate = randomExpirationDate.plusDays(days)
                                             .plusMonths(months)
                                             .plusYears(year);

        return randomExpirationDate;
    }

    // gerar codigo de segurança aleatório
    private int generateSecurityCode(){
        Random randomCode = new Random();
        return 100 + randomCode.nextInt(900); // vai gerar um número entre 100 e 999;
    }
    
    
    // Getters and Setters
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(int securityCode) {
        this.securityCode = securityCode;
    }

    public CardBrand getBrand() {
        return cardBrand;
    }

    public void setBrand(CardBrand cardBrand) {
        this.cardBrand = cardBrand;
    }

    public CardLevel getCardLevel() {
        return cardLevel;
    }

    public void setCardLevel(CardLevel cardLevel) {
        this.cardLevel = cardLevel;
    }

    
}