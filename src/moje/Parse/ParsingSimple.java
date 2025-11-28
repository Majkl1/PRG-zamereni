package moje.Parse;

public class ParsingSimple {
    public static void main(String[] args) {
        String neco = "tady je cislo:3";

        /*for (int i = 0; i < neco.length(); i++) {
            // Charakter a; má metody
            //char a; nemá metody
            if (Character.isDigit(neco.charAt(i))){
                System.out.println(neco.charAt(i));
                int num = Integer.parseInt(neco.charAt(i));
            }

        }*/



        //tokenizace
        String[] arr = neco.split(":");
        int num = Integer.parseInt(arr[1]);
        System.out.println(num+2);
    }
}
