/*
Anthony De Luca
Objects Assignment
7/5/2018
 */

import java.io.*;
import java.util.ArrayList;


public class The_Goon_Shop {

    public InputStreamReader inStream = new InputStreamReader(System.in);
    public BufferedReader sysin = new BufferedReader(inStream);
    private String password = "goon";
    private Boolean manager = false, modifying = true, shopping = true;
    private ArrayList<Item> inventory = new ArrayList<>();
    private ArrayList<Customer> customers = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        The_Goon_Shop constructor = new The_Goon_Shop();

    }

    public The_Goon_Shop() throws IOException {
        load();
        System.out.print("Welcome, if you are the manager please enter the password, if not, press enter(goon):");
        if (sysin.readLine().equals("goon")) {
            manager = true;
        }
        if (manager) {
            while (modifying) {
                for (Item item : inventory) {
                    System.out.println(item.toString());
                }
                System.out.println("Would you like to:\n1. Create a new item\n2. Remove an item\n3. Alter an item\n4. Exit");
                switch (Integer.parseInt(sysin.readLine())) {
                    case 1:
                        createNewItem();
                        break;
                    case 2:
                        removeItem();
                        break;
                    case 3:
                        alterItem();
                        break;
                    default:
                        modifying = false;
                        break;

                }
            }
        } else {
            System.out.println("Greetings Customer, please enter your password('0' for a new account): ");
            while (!checkAccessible()) {
                String password = sysin.readLine();
                for (Customer customer : customers
                        ) {
                    if (password.equals(customer.getPassword())) {
                        customer.setAccessible(true);
                    }
                }
                if (password.equals("0")) {
                    createNewCustomer();
                } else if (!checkAccessible()) {
                    System.out.println("You have inputted the wrong password, please try again");
                }
            }
            while (shopping) {
                System.out.println("Would you like to:\n1. Add an item to cart\n2. Remove an item from cart\n3. Pay bill\n4. Exit");
                switch (Integer.parseInt(sysin.readLine())) {
                    case 1:
                        addItemToCart();
                        break;
                    case 2:
                        removeItemFromCart();
                        break;
                    case 3:
                        payBill();
                        break;
                    default:
                        shopping = false;
                        break;

                }
            }
        }
        save();
    }

    public boolean checkAccessible() {
        for (Customer customer : customers
                ) {
            if (customer.isAccessible()) {
                return true;
            }
        }
        return false;
    }

    public void createNewCustomer() throws IOException {
        boolean okay = false;
        System.out.print("Please enter your account details:\nFirst Name: ");
        String firstName = sysin.readLine();
        System.out.print("Last Name: ");
        String lastName = sysin.readLine();
        System.out.print("Address: ");
        String address = sysin.readLine();
        while (!okay) {
            okay = true;
            System.out.print("Password:");
            String password = sysin.readLine();
            for (Customer customer : customers
                    ) {
                if (password.equals(customer.getPassword())) {
                    okay = false;
                }
            }
            if (!okay) {
                System.out.println("Please enter a different password.");
            }
        }
        System.out.print("Would you like to be a gold member?(y/n): ");
        if (sysin.readLine().equalsIgnoreCase("y")) {
            customers.add(new Customer(firstName, lastName, address, password, true, true));
        } else {
            customers.add(new Customer(firstName, lastName, address, password, false, true));
        }
    }

    public void addItemToCart() throws IOException {
        String name = "", type = "";
        double price = 0;
        int x = 1;
        ArrayList<Item> applicableInventory = new ArrayList<>();
        System.out.println("Would you like to search for the item based on:\n1. Name\n2. Type\n3. Price");
        switch (Integer.parseInt(sysin.readLine())) {
            case 1:
                System.out.print("Enter the item name:");
                name = sysin.readLine();
                break;
            case 2:
                System.out.print("Enter the item type:");
                type = sysin.readLine();
                break;
            case 3:
                System.out.print("Enter the item price:");
                price = Double.parseDouble(sysin.readLine());
                break;
        }

        System.out.println("These are the items matching your search:");
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getName().equals(name)) {
                System.out.println(inventory.get(i).toString());
                applicableInventory.add(inventory.get(i));
            } else if (inventory.get(i).getType().equals(type)) {
                System.out.println(inventory.get(i).toString());
                applicableInventory.add(inventory.get(i));
            } else if (inventory.get(i).getPrice() == price) {
                System.out.println(inventory.get(i).toString());
                applicableInventory.add(inventory.get(i));
            }
        }
        if (applicableInventory.size() < 1) {
            System.out.println("No items match your search.");
        } else {

            System.out.println("Which item would you like to add:");
            for (Item item : applicableInventory
                    ) {
                System.out.println(x + "." + " " + item.toString());
                x++;
            }

            x = Integer.parseInt(sysin.readLine());

            for (Customer customer : customers
                    ) {
                if (customer.isAccessible()) {
                    customer.setCart(applicableInventory.get(x - 1));
                }
            }
        }
    }

    public void removeItemFromCart() throws IOException {
        int x = 1;
        for (Customer customer : customers
                ) {
            if (customer.isAccessible() && customer.getCart().size() > 0) {
                for (Item item : customer.getCart()
                        ) {
                    System.out.println(x + ". " + item.toString());
                }
                System.out.println("Which item would you like to remove(#): ");
                x = Integer.parseInt(sysin.readLine());
                customer.getCart().remove(x - 1);
            }
            else {
                System.out.println("Your cart is empty.");
            }
        }
    }

    public void payBill() throws IOException {
        for (Customer customer : customers
                ) {
            if (customer.isAccessible()) {
                System.out.println("Your tab is: $" + customer.getTab().getFinalAmount());
                System.out.print("Please enter how much you are paying: $");
                customer.getTab().setPayment(Double.parseDouble(sysin.readLine()));
                System.out.println("Your new tab is: $" + customer.getTab().getFinalAmount());
            }
        }
    }

    public void createNewItem() throws IOException {
        System.out.print("Please insert the name, type, and price\nName: ");
        String name = sysin.readLine();
        System.out.print("Type: ");
        String type = sysin.readLine();
        System.out.print("Price: ");
        double price = Double.parseDouble(sysin.readLine());
        inventory.add(new Item(name, type, price));
    }

    public void removeItem() throws IOException {
        String name = "", type = "";
        int price = 0;
        for (Item item : inventory
                ) {
            System.out.println(item.toString());
        }
        System.out.println("Would you like to remove based on:\n1. Name\n2. Type\n3. Price");
        switch (Integer.parseInt(sysin.readLine())) {
            case 1:
                System.out.print("Enter the item name:");
                name = sysin.readLine();
                break;
            case 2:
                System.out.print("Enter the item type:");
                type = sysin.readLine();
                break;
            case 3:
                System.out.print("Enter the item price:");
                price = Integer.parseInt(sysin.readLine());
                break;
        }

        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getName().equals(name)) {
                inventory.remove(i);
            } else if (inventory.get(i).getType().equals(type)) {
                inventory.remove(i);
            } else if (inventory.get(i).getPrice() == price) {
                inventory.remove(i);
            }

        }
    }

    public void alterItem() throws IOException {
        String name = "", type = "", newName, newType;
        double price = 0, newPrice;
        int x = 1;
        ArrayList<Item> applicableInventory = new ArrayList<>();
        System.out.println("Would you like to search for the item based on:\n1. Name\n2. Type\n3. Price");
        switch (Integer.parseInt(sysin.readLine())) {
            case 1:
                System.out.print("Enter the item name:");
                name = sysin.readLine();
                break;
            case 2:
                System.out.print("Enter the item type:");
                type = sysin.readLine();
                break;
            case 3:
                System.out.print("Enter the item price:");
                price = Double.parseDouble(sysin.readLine());
                break;
        }

        System.out.println("These are the items matching your search:");
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getName().equals(name)) {
                System.out.println(inventory.get(i).toString());
                applicableInventory.add(inventory.get(i));
            } else if (inventory.get(i).getType().equals(type)) {
                System.out.println(inventory.get(i).toString());
                applicableInventory.add(inventory.get(i));
            } else if (inventory.get(i).getPrice() == price) {
                System.out.println(inventory.get(i).toString());
                applicableInventory.add(inventory.get(i));
            }
        }

        System.out.println("Which item would you like to alter:");
        for (Item item : applicableInventory
                ) {
            System.out.println(x + "." + " " + item.toString());
            x++;
        }

        x = Integer.parseInt(sysin.readLine());

        System.out.println("Would you like to alter the:\n1. Name\n2. Type\n3. Price");
        switch (Integer.parseInt(sysin.readLine())) {
            case 1:
                System.out.print("Enter the item name:");
                applicableInventory.get(x - 1).setName(sysin.readLine());
                break;
            case 2:
                System.out.print("Enter the item type:");
                applicableInventory.get(x - 1).setType(sysin.readLine());
                break;
            case 3:
                System.out.print("Enter the item price:");
                applicableInventory.get(x - 1).setPrice(Double.parseDouble(sysin.readLine()));
                break;
        }


        for (Item item : inventory
                ) {
            if (applicableInventory.get(x - 1).equals(item)) {
                item = applicableInventory.get(x - 1);
            }
        }
    }

    public void save() {
        String fileName = "goonList.txt";
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(String.valueOf(inventory.size()));
            bufferedWriter.newLine();
            for (int i = 0; i < inventory.size(); i++) {
                bufferedWriter.write(inventory.get(i).getName());
                bufferedWriter.newLine();
                bufferedWriter.write(inventory.get(i).getType());
                bufferedWriter.newLine();
                bufferedWriter.write(String.valueOf(inventory.get(i).getPrice()));
                bufferedWriter.newLine();
            }
            bufferedWriter.write(String.valueOf(customers.size()));
            bufferedWriter.newLine();
            for (int i = 0; i < customers.size(); i++) {
                bufferedWriter.write(customers.get(i).getFirstName());
                bufferedWriter.newLine();
                bufferedWriter.write(customers.get(i).getLastName());
                bufferedWriter.newLine();
                bufferedWriter.write(customers.get(i).getAddress());
                bufferedWriter.newLine();
                bufferedWriter.write(customers.get(i).getPassword());
                bufferedWriter.newLine();
                bufferedWriter.write(String.valueOf(customers.get(i).isGoldMember()));
                bufferedWriter.newLine();
                bufferedWriter.write(String.valueOf(customers.get(i).getTab().getFinalAmount()));
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException ioe) {
        }
    }

    public void load() {
        String fileName = "goonList.txt";
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            bufferedReader.mark(1);
            if (bufferedReader.readLine() != null) {
                bufferedReader.reset();
                for (int i = Integer.parseInt(bufferedReader.readLine()); i > 0; i--) {
                    inventory.add(new Item(bufferedReader.readLine(), bufferedReader.readLine(), Double.parseDouble(bufferedReader.readLine())));
                }
                for (int i = Integer.parseInt(bufferedReader.readLine()), j = 0; i > 0; i--, j++) {
                    customers.add(new Customer(bufferedReader.readLine(), bufferedReader.readLine(), bufferedReader.readLine(), bufferedReader.readLine(), Boolean.valueOf(bufferedReader.readLine()), false));
                    customers.get(j).getTab().setFinalAmount(Double.parseDouble(bufferedReader.readLine()));
                }
                bufferedReader.close();
            }
        } catch (IOException ioe) {
        }
    }
}
