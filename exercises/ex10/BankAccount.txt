// Class for Exercise 10

public class BankAccount {
    private String accountNumber;
    protected int balance;

    public BankAccount(String num) {
        if (num.isBlank()) {
            throw new IllegalArgumentException("invalid account number");
        }
        accountNumber = num;
        balance = 0;
    }

    public BankAccount(String num, int bal) {
        if (num.isEmpty()) {
            throw new IllegalArgumentException("invalid account number");
        }
        if (bal < 0) {
            throw new IllegalArgumentException("invalid initial balance");
        }

        accountNumber = num;
        balance = bal;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public int getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return String.format("%s: number=%s, balance=%d", getClass().getName(),
            accountNumber, balance);
    }

    public void deposit(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("invalid amount");
        }

        balance += amount;
    }

    public void withdraw(int amount) {
        checkWithdrawal(amount);
        balance -= amount;
    }

    protected void checkWithdrawal(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("invalid amount");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("insufficient funds for withdrawal");
        }
    }
}