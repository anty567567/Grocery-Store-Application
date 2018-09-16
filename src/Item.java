public class Item {

    private String name, type;
    private double price;

    public Item(String name, String type, double price){
        this.name = name;
        this.type = type;
        this.price = price;

    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "Name: " + name + "  Type: " + type + "  Price: $" + price;
    }

    @Override
    public boolean equals(Object obj) {
        Item x = (Item) obj;
        return (name.equals(x.getName()) && price == x.getPrice() && type.equals(x.getType()));
    }
}