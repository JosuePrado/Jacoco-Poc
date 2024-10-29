package myLibrary;

import java.io.IOException;

public class Main {
    public static void main(String[] args)  {

        try {
            InstrumentCode iCode = new InstrumentCode();
            iCode.addPrintToByCode("build\\classes\\java\\main\\myLibrary\\Calculator.class", "rest");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // try {
        //     InstrumentCode2 iCode = new InstrumentCode2();
        //     iCode.addCounterToByteCode("build\\classes\\java\\main\\myLibrary\\Calculator.class", "sum");
        // } catch (IOException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }

        // Calculator calculator = new Calculator();
        // calculator.sum(0, 0);

        // // Obtiene el valor del contador y lo imprime
        // int count = calculator.getMethodCallCount();
        // System.out.println("Method someMethod was called " + count + " times.");
    }
}