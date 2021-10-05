
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
