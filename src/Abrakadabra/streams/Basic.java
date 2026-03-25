package Abrakadabra.streams;

@FunctionalInterface
interface MathFunction{
    double run(double a, double b);
}

interface Printable{
    void print();
}

interface Magnitude{
    double quadratic(double number);
}

public class Basic {
    public static void printResult(MathFunction mf, double a, double b){
        System.out.println(mf.run(a,b));
    }



    public static void main(String[] args) {
        MathFunction add = new Addition();
        System.out.println(add.run(5,6));

        MathFunction sub = new MathFunction() {
            @Override
            public double run(double a, double b) {
                return a - b;
            }
        };

        printResult(add, 5, 6);
        printResult(sub,10,4);

        printResult((a, b) -> a * b, 5, 6);

        Printable printable = () -> System.out.println("ahoj");

        Magnitude mag = (n) -> {
            System.out.println(n * n);
            return n * n;
        };

    }
}

class Addition implements MathFunction{

    @Override
    public double run(double a, double b) {
        return a + b;
    }
}