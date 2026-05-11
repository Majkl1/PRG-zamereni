package Abrakadabra.filing.cviceni;

import java.io.File;
import java.io.IOException;

public class bouracka {
    public static void main(String[] args) {
        File f = new File("data/Eaters.txt");

        try {
            System.out.println(f.getAbsoluteFile());
            System.out.println(f.getCanonicalPath());
            System.out.println(f.getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}


class File2 implements Comparable{
    int hell = 5;

    public int getHell() {
        return hell;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}