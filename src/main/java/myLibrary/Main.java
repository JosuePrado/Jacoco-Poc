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
    }
}