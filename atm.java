import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class AccountManager {
    private HashMap<String, String> userCredentials;
    private HashMap<String, BankAccount> accounts;

    public AccountManager() {
        userCredentials = new HashMap<>();
        accounts = new HashMap<>();

        userCredentials.put("123456", "1111");
        accounts.put("123456", new BankAccount("John Doe", "123456", 5000.0));

        userCredentials.put("789101", "2222");
        accounts.put("789101", new BankAccount("Alice Smith", "789101", 7000.0));
    }

    public BankAccount validateUser(String accNumber, String pin) {
        if (userCredentials.containsKey(accNumber) && userCredentials.get(accNumber).equals(pin)) {
            return accounts.get(accNumber);
        }
        return null;
    }
}

class BankAccount {
    private String accountHolder;
    private String accountNumber;
    private double balance;
    private ArrayList<String> transactionHistory;

    public BankAccount(String accountHolder, String accountNumber, double balance) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: $" + amount);
            System.out.println("Deposit Successful. New Balance: $" + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrawn: $" + amount);
            System.out.println("Withdrawal Successful. Remaining Balance: $" + balance);
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }

    public void checkBalance() {
        System.out.println("Current Balance: $" + balance);
    }

    public void showTransactionHistory() {
        System.out.println("Transaction History:");
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            for (String transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }
}

public class ATM {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AccountManager accountManager = new AccountManager();

        System.out.print("Enter Account Number: ");
        String accNumber = scanner.nextLine();

        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        BankAccount userAccount = accountManager.validateUser(accNumber, pin);

        if (userAccount == null) {
            System.out.println("Invalid Credentials! Exiting...");
            return;
        }

        System.out.println("Welcome, " + accNumber + "!");

        while (true) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. View Mini Statement");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    userAccount.deposit(depositAmount);
                    break;
                case 2:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount = scanner.nextDouble();
                    userAccount.withdraw(withdrawAmount);
                    break;
                case 3:
                    userAccount.checkBalance();
                    break;
                case 4:
                    userAccount.showTransactionHistory();
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
