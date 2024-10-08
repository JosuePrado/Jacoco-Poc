import myLibrary.BankAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BankAccountTest {

    @Test
    public void testDeposit() {
        BankAccount account = new BankAccount("12345", 100);
        account.deposit(50);
        assertEquals(150, account.getBalance());
    }

    @Test
    public void testWithdraw() {
        BankAccount account = new BankAccount("12345", 100);
        account.withdraw(50);
        assertEquals(50, account.getBalance());
    }

    @Test
    public void testWithdrawInvalidAmount() {
        BankAccount account = new BankAccount("12345", 100);
        assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(150);
        });
    }

    @Test
    public void testDepositNegativeAmount() {
        BankAccount account = new BankAccount("12345", 100);
        assertThrows(IllegalArgumentException.class, () -> {
            account.deposit(-10);
        });
    }
}