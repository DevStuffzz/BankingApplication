
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
/*
	Corey Beaver
	Last Edited 12.14.2021

	GUI.java
	This class handles all of the graphical side of the application
	Still just JOptionPane, but I will update the application to use
	the Swing library, for an actaul graphical user interface, that can 
	run on all platforms.

	This class also contains our entry point
 */
public class GUI {
	// The list of all of the accounts
	private static List<BankAccount> accounts;

	public static void main(String[] args) throws IOException {

		// Serialize all objects
		accounts = FileSystem.serializeAccounts("accounts.txt");


		boolean running = true;

		// Contains the current accounts' data
		String name;
		double ammount;

		while(running) {
			// Get the current command
			String command = JOptionPane.showInputDialog("Command (create, deposit, withdraw, check, close)");
			switch (command) {
			case "create":
				name = JOptionPane.showInputDialog("Name");
				ammount = Double.parseDouble(JOptionPane.showInputDialog("Deposit"));
				JOptionPane.showMessageDialog(null, "Created Account " + name);
				BankAccount accountToCreate = new BankAccount(name, ammount);
				FileSystem.writeAccount(accountToCreate);
				accounts.add(accountToCreate);
				break;
			case "deposit":
				name = JOptionPane.showInputDialog("Name");
				ammount = Double.parseDouble(JOptionPane.showInputDialog("Deposit"));
				for(BankAccount acc : accounts){
					if(acc.name.equals(name)) {
						acc.deposit(ammount);
						FileSystem.updateAmmount(accounts);
					}
				}
				break;
			case "withdraw":
				name = JOptionPane.showInputDialog("Name");
				ammount = Double.parseDouble(JOptionPane.showInputDialog("Withdraw"));
				for(BankAccount acc : accounts){
					if(acc.name.equals(name)) {
						acc.withdraw(ammount);
						FileSystem.updateAmmount(accounts);
					}
				}
				break;
			case "check":
				name = JOptionPane.showInputDialog("Name");
				for(BankAccount acc : accounts){
					if(acc.name.equals(name)) {
						JOptionPane.showMessageDialog(null, "Account " + acc.name + " has $" + acc.getAmmount());

					}
				}
				break;
			case "close":
				running = false;
				break;
			case "clear the file for testing":
				FileSystem.clearFile();
				break;
			default:
				JOptionPane.showMessageDialog(null, "Error, Invalid Command");
			}
		}
	}
}
