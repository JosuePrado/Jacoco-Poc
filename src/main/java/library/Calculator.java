package library;

public class Calculator {
    public static int sum(int a, int b) {
        return a + b;
    }

    public static int rest(int a, int b) {
        return a - b;
    }

    public static int mult(int a, int b) {
        return a * b;
    }

    public static int div(int a, int b) {
        return a / b;
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        AuxC auxC = new AuxC();
        int number = 2;
        int number2 = 3;
        System.out.println("Sum:" + calculator.sum(number, number2));
    }
}
