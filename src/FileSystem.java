import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.Scanner;

/*
 * Corey Beaver, AP Computer Science A
 * 10.4.2021
 * Last Edited 12.14.2021
 *
 * This class takes care of everything to do with files
 * Without this class, we would have to manually create all accounts everytime we started the program
 * This will save accounts to a file and Dynamically read and write to the file
 * It is a very basic database as it is just a .txt file
 *
 * */
public class FileSystem {

	/**
	 * Returns a list of all bank accounts in the given file
	 */
	public static List<BankAccount> serializeAccounts(String location) {
		// Create an empty list, we will return this list
		List<BankAccount> accounts = new ArrayList<BankAccount>();

		// File and scanner for reading the file
		File file;
		Scanner inputFile;

		// Try to open the file and if it does not exist, create it
		try {
			// This will allow us to read the file
			file = new File(location);
			inputFile = new Scanner(file);

			// Nessasary For Createing the account dynamically
			String first = "";
			String last = "";
			Double ammount;

			// Will tell what String/Double we are on
			int iterator = 0;
			while (inputFile.hasNext()) {

				// Get The First Name
				if (iterator == 0) {
					first = inputFile.next();
					iterator++;
				}
				// Get the Last Name
				else if (iterator == 1) {
					last = inputFile.next();
					iterator++;
				}
				// Get the account balance
				else if (iterator == 2) {
					ammount = inputFile.nextDouble();
					// Create an account given the current line that we are on
					accounts.add(new BankAccount((first + " " + last), ammount));
					// Reset the iterator so that we can read multiple accounts
					iterator = 0;
				}

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			// Create a file
		}

		return accounts;
	}

	/*
	 *
	 * Writes a new line on the account list that will be serialized on startup
	 *
	 */
	public static void writeAccount(BankAccount account) {
		// Try to open the file
		try {
			// The writer opens the file and makes it so that we can use the
			// print writer to write to the file
			FileWriter writer = new FileWriter("accounts.txt", true);
			// Create a PrintWriter so we can write and writeln directly to
			// the file
			PrintWriter pWriter = new PrintWriter(writer);
			// Write the new account to the file
			pWriter.println(account.name + " " + account.getAmmount());
			pWriter.close();
		}
		// If we cannot open the file
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * returns a String of the given file
	 *
	 */
	public static void updateAmmount(List<BankAccount> accounts) throws IOException {
		// Try to open the file
		try {
			// Clear the file
			clearFile();
			// FileWriter is a class that will allow us to append something to a
			// file (Another account)
			FileWriter writer = new FileWriter("accounts.txt", true);
			// Try to write to the file
			// Create a PrintWriter so we can write and writeln directly to
			// the file
			PrintWriter pWriter = new PrintWriter(writer);
			System.out.println(accounts);
			// Create an empty string to store the file into
			String sAccounts = "";
			for (BankAccount account : accounts) {
				// Rewrite the file with the accounts
				sAccounts += account.name + " " + account.getAmmount() + "\n";
			}
			System.out.println(sAccounts);
			pWriter.println(sAccounts);
			pWriter.close();
		}
		// If we cannot open the file
		catch (FileNotFoundException e) {
			// Create the file
			File file = new File("accounts.txt");
			file.createNewFile();
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
		Clear the files content, erasing all account data
	 */
	public static void clearFile() {
		FileWriter fwOb;
		try {
			fwOb = new FileWriter("accounts.txt", false);
			PrintWriter pwOb = new PrintWriter(fwOb, false);
			pwOb.flush();
			pwOb.close();
			fwOb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
