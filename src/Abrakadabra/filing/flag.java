package Abrakadabra.filing;

import java.io.*;

public class flag {

    static String findFlag(File root){
        try {
            BufferedReader br = new BufferedReader(new FileReader(root));
            String line;
            int lineNum = 0;
            while ((line = br.readLine()) != null){
                lineNum++;
                String[] parts = line.split(";");
                if (parts[0].length() != Integer.parseInt(parts[1]) || !reverse(parts[0]).equals(parts[2])){
                    String[] flag = parts[2].split("[{}]");
                    return "Vlajka: " + flag[1] + "; " + "soubor: " + root.getName() + "; řádek:" + lineNum;
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("file not found!");
        } catch (IOException e) {
            System.out.println("Something went wrong!" + e.getMessage());
        }
        return null;
    }

    static String reverse(String originalStr){
        String reversedStr = "";

        for (int i = 0; i < originalStr.length(); i++) {
            reversedStr = originalStr.charAt(i) + reversedStr;
        }
        return reversedStr;
    }

    static void printFlag(File root){

        if (!root.exists()){
            return;
        }
        if (!root.isDirectory()){
            if (findFlag(root) != null){
                System.out.println(findFlag(root));
            }
        } else {
            File[] content = root.listFiles();
            for (File file: content){
                printFlag(file);
            }
        }
    }


    public static void main(String[] args) {
        File f = new File("data/ctf");
        printFlag(f);
    }
}
