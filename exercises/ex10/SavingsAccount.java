// Class represents a savings account
// Inherits from BankAccount

public class SavingsAccount extends BankAccount {

  // private fields to represent interest of savings account
  private double interestRate;

  // constructor that initialises a savings account object (default)
  public SavingsAccount(String num, int bal) {
    this(num, bal, 0.05);
  }

  /**
  * Creates a savings account with account number, balance and interest rate.
  *
  * @param num Value for account number
  * @param bal Value for balance
  * @param rate Value for interest rate
  */ 
  public SavingsAccount(String num, int bal, double rate) {
    super(num, bal);
    setInterestRate(rate);
  }

  /**
   * @return Current interest rate
   */
  public double getInterestRate() {
    return interestRate;
  }

  // sets new interest rate
  public void setInterestRate(double rate) {
    if (rate <= 0) {
      throw new IllegalArgumentException("invalid rate of interest");
    }
    else {
      interestRate = rate;
    }
  }

  // applies interest
  public void applyInterest() {
    double doubleInterest = balance * interestRate;
    int intInterest = (int) doubleInterest;
    balance += intInterest;
  }

  // checkWithdrawal method, only permirring withdrawals if balance will remain above 100 

  @Override
  protected void checkWithdrawal(int amount) {
    if (amount <= 0) {
      throw new IllegalArgumentException("invalid amount");
    }
    if (amount > balance || balance - amount < 100) { // changed line
      throw new IllegalArgumentException("insufficient funds for withdrawal");
    }
  }

} // end of class SavingsAccount