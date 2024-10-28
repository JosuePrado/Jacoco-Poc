import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import myLibrary.Calculator;

public class CalculatorTest {
    
    @Test
    public void testSum() {
        assertEquals(8, Calculator.sum(5, 3));
    }

    @Test
    public void testRest() {
        assertEquals(2, Calculator.rest(5, 3));
    }

    @Test
    public void testMult() {
        assertEquals(15, Calculator.mult(5, 3));
    }

    @Test
    public void testDiv() {
        assertEquals(2, Calculator.div(5, 2));
    }
}
