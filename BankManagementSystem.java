package com;
import java.util.*;

class Bank {
    private String name;
    private List<Account> accounts;

    public Bank(String name) {
        this.name = name;
        this.accounts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void printSummary() {
        System.out.println("Bank Name: " + name);
        for (Account account : accounts) {
            System.out.println("Account ID: " + account.getId() + " | Customer Name: " + account.getCustomerName() + " | Address: " + account.getAddress() + " | Balance: " + account.getBalance());
        }
    }
}

class Account {
    private String id;
    private String customerName;
    private String address;
    private double balance;

    public Account(String id, String customerName, String address, double balance) {
        this.id = id;
        this.customerName = customerName;
        this.address = address;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getAddress() {
        return address;
    }

    public double getBalance() {
        return balance;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void updateBalance(double amount) {
        balance += amount;
    }
}

public class BankManagementSystem {
    private static Map<String, Bank> banks = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("1. Add New Account");
            System.out.println("2. Change Address of the customer");
            System.out.println("3. Check Account balance");
            System.out.println("4. Update Account balance");
            System.out.println("5. Summary of All Accounts");
            System.out.println("6. Quit");
            System.out.println("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addNewAccount(scanner);
                    break;
                case 2:
                    changeAddress(scanner);
                    break;
                case 3:
                    checkBalance(scanner);
                    break;
                case 4:
                    updateBalance(scanner);
                    break;
                case 5:
                    printSummary();
                    break;
                case 6:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 6);
    }

    private static void addNewAccount(Scanner scanner) {
        System.out.println("Enter Bank Name: ");
        String bankName = scanner.next();
        System.out.println("Enter Customer Name: ");
        String customerName = scanner.next();
        System.out.println("Enter Initial Amount: ");
        double initialAmount = scanner.nextDouble();
        String accountId = generateAccountId(bankName);
        Account account = new Account(accountId, customerName, "", initialAmount);

        if (!banks.containsKey(bankName)) {
            Bank bank = new Bank(bankName);
            bank.addAccount(account);
            banks.put(bankName, bank);
        } else {
            banks.get(bankName).addAccount(account);
        }

        System.out.println("Account created successfully with ID: " + accountId);
    }

    private static void changeAddress(Scanner scanner) {
    	
    	    System.out.println("Enter Account ID: ");
    	    String accountId = scanner.next();
    	    Account account = findAccountById(accountId);

    	    if (account != null) {
    	        System.out.println("Enter New Address: ");
    	        String address = scanner.next();
    	        account.setAddress(address);
    	        System.out.println("Address Changed Successfully.");
    	    } else {
    	        System.out.println("Account not found. Please try again.");
    	    }
    	}

        		private static void checkBalance(Scanner scanner) {
            System.out.println("Enter Account ID: ");
            String accountId = scanner.next();
            Account account = findAccountById(accountId);

            if (account != null) {
                System.out.println("Account Balance: " + account.getBalance());
            } else {
                System.out.println("Account not found. Please try again.");
            }
        }

        private static void updateBalance(Scanner scanner) {
            System.out.println("Enter Account ID: ");
            String accountId = scanner.next();
            Account account = findAccountById(accountId);

            if (account != null) {
                System.out.println("Enter Amount to Update: ");
                double amount = scanner.nextDouble();
                account.updateBalance(amount);
                System.out.println("Account Balance Updated Successfully.");
            } else {
                System.out.println("Account not found. Please try again.");
            }
        }

        private static void printSummary() {
            for (Bank bank : banks.values()) {
                bank.printSummary();
            }
        }

        private static String generateAccountId(String bankName) {
            int numAccounts = banks.containsKey(bankName) ? banks.get(bankName).getAccounts().size() : 0;
            String accountId = bankName + String.format("%04d", numAccounts + 1);
            return accountId;
        }

        private static Account findAccountById(String accountId) {
            for (Bank bank : banks.values()) {
                for (Account account : bank.getAccounts()) {
                    if (account.getId().equals(accountId)) {
                        return account;
                    }
                }
            }
            return null;
        }
    }



               



	
