

/*
	Corey Beaver

	BankAccount.java
	This class contians data for a bank account. These variables include the name and ammount. 
	The ammount is the current ballence of the bank account.
*/
public class BankAccount {
	public String name;
	private double ammount;

	public BankAccount(String name, double ammount) {
		this.name = name;
		this.ammount = ammount;
	}

	public void deposit(double ammount) {
		this.ammount+=ammount;
	}

	public void withdraw(double ammount) {
		this.ammount -= ammount;
	}

	public double getAmmount() {
		return this.ammount;
	}
}
