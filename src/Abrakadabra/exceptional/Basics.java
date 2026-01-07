package Abrakadabra.exceptional;

import java.util.ArrayList;
import java.util.Scanner;

public class Basics {

    static void magicPause(int amount) {
        System.out.println("bdjkbvjksd");
        //zodpovědně:
        try {
            Thread.sleep(amount * 1000L);
        } catch (InterruptedException e) {
            System.out.println("Program died in sleep :)");
        }
        //nezodpovědně:
        //Thread.sleep(amount * 1000L);
        System.out.println("Wakey, wakey");
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner("2");
        try {
            int input = sc.nextInt();
        } catch (ArithmeticException a) {
            System.out.println("Neděl nulou!");
        } catch (NumberFormatException e) {
            System.out.println("Zadej cislo.");
        }

        int[] array = {1,2};

        //jak to nedělat:
//        try {
//            System.out.println(array[5]);
//        } catch (Exception e) {
//            System.out.println(":(");
//        }
        magicPause(4);


    }
}
