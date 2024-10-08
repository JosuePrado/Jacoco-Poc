import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import myLibrary.Calculator;

public class CalculatorTest {
    
    @Test
    public void testSum() {
        assertEquals(8, Calculator.Sum(5, 3));
    }

    @Test
    public void testRest() {
        assertEquals(2, Calculator.Rest(5, 3));
    }

    @Test
    public void testMult() {
        assertEquals(15, Calculator.Mult(5, 3));
    }
}
