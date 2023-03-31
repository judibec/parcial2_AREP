package org.example;

public class collatzSequence {
    public static void main(String[] args) {
        int secuence = 13;
        System.out.println(createSecuence(13));
    }

    public static String createSecuence(int number) {
        String res = Integer.toString(number);
        while (number!=1){
            if (number%2 == 0){
                number=number/2;
                res+= "->" + Integer.toString(number);
            }else{
                number= (3*number) +1;
                res+= "->" + Integer.toString(number);
            }
        }
        return res;
    }
}
