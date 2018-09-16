import java.util.ArrayList;

public class Customer {

    private Bill tab = new Bill();
    private String firstName, lastName, address, password;
    private boolean goldMember, accessible;
    private ArrayList<Item> cart = new ArrayList<>();

    public Customer(String firstName, String lastName, String address, String password, boolean goldMember, boolean accessible) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.goldMember = goldMember;
        this.password = password;
        this.accessible = accessible;
        if (goldMember){
            goldMemberCost();
        }
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public Bill getTab() {
        return this.tab;
    }

    public String getAddress() {
        return address;
    }

    public boolean isGoldMember() {
        return goldMember;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAccessible() {
        return accessible;
    }

    public ArrayList<Item> getCart() {
        calculateBill();
        return cart;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setTab(Bill tab) {
        this.tab = tab;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setGoldMember(boolean goldMember) {
        this.goldMember = goldMember;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccessible(boolean accessible) {
        this.accessible = accessible;
    }

    public void setCart(Item item) {
        cart.add(item);
        calculateBill();
    }

    public void goldMemberCost(){
        tab.setAmount(15);
        tab.setDiscount(0.9);
    }

    public void calculateBill(){
        tab.setAmount(0);
        for (Item item:cart
             ) {
            tab.setAmount(tab.getAmount() + item.getPrice());
        }
        if (goldMember){
            tab.setAmount(tab.getAmount() + 15);
        }
    }

    @Override
    public String toString() {
        return "Name: " + firstName + " " + lastName + "    Address: " + address + "    Gold Member: " + goldMember + " Amt Due: $" + tab.getFinalAmount();
    }
}
