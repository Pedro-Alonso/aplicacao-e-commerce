package ECommerceApp.src.Classes;

import java.time.LocalDate;
import java.util.Random;

public class CardInfoFactory {
    // gerar os 16 números do cartão - Algoritmo de Luhn

    /**
     * Gerar um número de cartão
     * Utilizamos o Algoritmo de Luhn para criação coesa do cartão
     * 
     * @return um número de cartão com 16 dígitos seguro
    */
    public static String generateCardNumber(){
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

    /***
     * Algoritmo de Luhn
     * É um método para criar números de cartão de forma coesa. Sua lógica é de 
     * inverter os numeros 
     * somar os números na posiçõa ímpar (s1)
     * multiplicar por 2 os números na posição impar
     * soma os algoritmos da soma separadamente (16 -> 1+6=7) ou se ele for > 9, subtrair 9
     * somar os algoritmos juntos (s2)
     * se s1+s2 tem como último digito 0, ele é válido
     * 
     * @param card é o array com o número do cartão
     * @return o último número do cartão
     */
    private static int LuhnAlgorithm(int card[]){
        
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

    /***
     * Gerar uma data de expiração aleatória
     * Escolhe uma data, a partir da criação, que vai ate até 10 anos pra frente
     * 
     * @return uma data de validade aleatória
     */
    public static LocalDate generateExpirationDate(){
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

    /***
     * Gerar um código de segurança
     * Gera um número de 100 a 999 como código de segurança
     * 
     * @return um valor inteiro
     */
    public static int generateSecurityCode(){
        Random randomCode = new Random();
        return 100 + randomCode.nextInt(900); 
    }
}
