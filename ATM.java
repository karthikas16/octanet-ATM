import java.util.ArrayList;
import java.util.Scanner;

class ATM {
    private double balance;
    private String pin;
    private ArrayList<String> transactionHistory;

    // Constructor to initialize ATM with a starting balance and PIN
    public ATM(double initialBalance, String initialPin) {
        this.balance = initialBalance;
        this.pin = initialPin;
        this.transactionHistory = new ArrayList<>();
    }

    // Method to inquire the account balance
    public void inquireBalance() {
        System.out.println("Your current balance is: $" + balance);
        transactionHistory.add("Balance inquiry: $" + balance);
    }

    // Method to withdraw cash
    public void withdrawCash(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount. Please enter a positive value.");
            return;
        }
        if (amount <= balance) {
            balance -= amount;
            System.out.println("You have successfully withdrawn $" + amount);
            transactionHistory.add("Withdrawal: $" + amount);
        } else {
            System.out.println("Insufficient balance. Transaction failed.");
        }
    }

    // Method to deposit cash
    public void depositCash(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid deposit amount. Please enter a positive value.");
            return;
        }
        balance += amount;
        System.out.println("You have successfully deposited $" + amount);
        transactionHistory.add("Deposit: $" + amount);
    }

    // Method to change the PIN
    public void changePin(String newPin) {
        this.pin = newPin;
        System.out.println("Your PIN has been successfully changed.");
        transactionHistory.add("PIN changed");
    }

    // Method to display transaction history
    public void displayTransactionHistory() {
        System.out.println("Transaction History:");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }

    // Method to verify PIN
    public boolean verifyPin(String inputPin) {
        return this.pin.equals(inputPin);
    }

    // Main method to simulate the ATM machine
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize the ATM with an initial balance and a default PIN
        ATM atm = new ATM(1000.0, "1234");

        System.out.println("Welcome to the ATM Machine!");

        // Verify the PIN before allowing access to other functionalities
        System.out.print("Please enter your PIN: ");
        String inputPin = scanner.nextLine();

        if (!atm.verifyPin(inputPin)) {
            System.out.println("Incorrect PIN. Access denied.");
            return;
        }

        while (true) {
            // Display options to the user
            System.out.println("\nPlease choose an option:");
            System.out.println("1. Balance Inquiry");
            System.out.println("2. Withdraw Cash");
            System.out.println("3. Deposit Cash");
            System.out.println("4. Change PIN");
            System.out.println("5. Transaction History");
            System.out.println("6. Exit");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear invalid input
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    atm.inquireBalance();
                    break;
                case 2:
                    System.out.print("Enter the amount to withdraw: $");
                    if (scanner.hasNextDouble()) {
                        double withdrawAmount = scanner.nextDouble();
                        atm.withdrawCash(withdrawAmount);
                    } else {
                        System.out.println("Invalid input. Please enter a valid amount.");
                        scanner.next();
                    }
                    break;
                case 3:
                    System.out.print("Enter the amount to deposit: $");
                    if (scanner.hasNextDouble()) {
                        double depositAmount = scanner.nextDouble();
                        atm.depositCash(depositAmount);
                    } else {
                        System.out.println("Invalid input. Please enter a valid amount.");
                        scanner.next();
                    }
                    break;
                case 4:
                    System.out.print("Enter your new PIN: ");
                    String newPin = scanner.nextLine();
                    atm.changePin(newPin);
                    break;
                case 5:
                    atm.displayTransactionHistory();
                    break;
                case 6:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
