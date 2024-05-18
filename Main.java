import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create an inventory
        Inventory inventory = new Inventory();

        // Create a scanner to read user input
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);

        // Display the current list of drugs
        System.out.println("Current list of drugs:");
        for (Drug drug : inventory.getDrugs()) {
            System.out.println(drug);
        }

        // Prompt the user to choose an option
        System.out.println("\nChoose an option:");
        System.out.println("1. Add a drug");
        System.out.println("2. Edit a drug");
        System.out.println("3. Record sales");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        // Perform the chosen action
        switch (choice) {
            case 1:
                // Add a drug
                System.out.print("Enter the name of the drug: ");
                scanner.nextLine(); // consume newline character
                String name = scanner.nextLine();
                System.out.print("Enter the price of the drug: ");
                double price = scanner.nextDouble();
                System.out.print("Enter the initial quantity of the drug: ");
                int quantity = scanner.nextInt();
                inventory.addDrug(new Drug(quantity, name, price, quantity));
                System.out.println("Drug added successfully!");
                break;
            case 2:
                // Edit a drug
                System.out.print("Enter the name of the drug to edit: ");
                scanner.nextLine(); // consume newline character
                String nameEdit = scanner.nextLine();
                Drug drugToEdit = inventory.getDrugByName(nameEdit);
                if (drugToEdit == null) {
                    System.out.println("Drug not found.");
                    break;
                }
                System.out.print("Enter the new price of the drug: ");
                double newPrice = scanner.nextDouble();
                System.out.print("Enter the new quantity of the drug: ");
                int newQuantity = scanner.nextInt();
                inventory.editDrug(drugToEdit, newPrice, newQuantity);
                System.out.println("Drug edited successfully!");
                break;
            case 3:
                // Record sales
                System.out.print("Enter the number of sales to record: ");
                int numSales = scanner.nextInt();
                while (numSales <= 0) {
                    System.out.print("Please enter a positive integer: ");
                    numSales = scanner.nextInt();
                }
                // Create an array to store the sale objects
                Sale[] sales = new Sale[numSales];

                // Record the sales
                for (int i = 0; i < numSales; i++) {
                    // Prompt the user for input
                    System.out.println("Enter sale " + (i+1) + " details:");
                    System.out.print("Drug name: ");
                    scanner.nextLine(); // consume newline character
                    String drugName = scanner.nextLine();
                    Drug saleDrug = inventory.getDrugByName(drugName);
                    if (saleDrug == null) {
                        System.out.println("Drug not found.");
                        i--;
                        continue;
                    }
                    System.out.print("Quantity: ");
                    int saleQuantity = scanner.nextInt();
                    while (saleQuantity <= 0) {
                        System.out.print("Please enter a positive integer: ");
                        saleQuantity = scanner.nextInt();
                    }System.out.print("Date (yyyy-MM-dd): ");
                    String dateInput = scanner.next();
                    DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
                    LocalDate date = null;
                    try {
                        date = LocalDate.parse(dateInput, formatter);
                    } catch (DateTimeParseException e) {
                        System.out.println("Please enter the date in the format yyyy-MM-dd: ");
                        dateInput = scanner.next();
                        date = LocalDate.parse(dateInput, formatter);
                    }

                    // Record the sale
                    try {
                        sales[i] = recordSale(inventory, saleDrug, saleQuantity, date);
                    } catch (IllegalArgumentException | InventoryException e) {
                        System.out.println(e.getMessage());
                        i--;
                    }
                }

                // Generate a report
                Report report = new Report();
                for (Sale sale : sales) {
                    report.addSale(sale);
                }
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    // Method to record a sale
    public static Sale recordSale(Inventory inventory, Drug drug, int quantity, LocalDate date) throws InventoryException {
        // Check if the quantity is available in the inventory
        if (inventory.getQuantity(drug) < quantity) {
            throw new InventoryException("Insufficient quantity in inventory");
        }

        // Deduct the quantity from the inventory
        inventory.deductQuantity(drug, quantity);

        // Calculate the revenue for the sale
        double revenue = drug.getPrice() * quantity;

        // Create a new sale object
        Sale sale = new Sale(drug, quantity, date, revenue);

        // Return the sale object
        return sale;
    }
}