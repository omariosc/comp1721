// File to test SavingsAccount and BankAccount classes

public class SavingsTest {
  public static void main(String[] args) {
    SavingsAccount acc = new SavingsAccount("123", 100);
    System.out.println(acc);
    acc.deposit(100);
    System.out.println(acc);
    acc.withdraw(100);
    System.out.println(acc);
    acc.withdraw(100); // take balance to 0 if successful withdrawal
    System.out.println(acc);
    acc.applyInterest();
    System.out.println(acc);
    acc.setInterestRate(100);
    acc.applyInterest();
    System.out.println(acc);
  } // end of main
} // end of class SavingsTest