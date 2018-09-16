public class Bill {

    private double amount, discount, tax, finalAmount, payment;
    public Bill(){
        amount = 0;
        discount = 0;
        tax = 0;
        finalAmount = 0;
        payment = 0;
    }

    public double getAmount() {
        return amount;
    }

    public double getFinalAmount() {
        return finalAmount;
    }

    public double getDiscount() {
        return discount;
    }

    public double getTax() {
        return tax;
    }

    public double getPayment() {
        return payment;
    }

    public void setAmount(double amount) {
        this.amount = amount;
        tax = amount*0.13;
        calculateFinal();

    }

    public void setFinalAmount(double finalAmount) {
        this.finalAmount = finalAmount;
        amount = finalAmount/1.13;
        tax = amount*0.13;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public void setPayment(double payment) {
        this.payment = payment;
        calculateFinal();
    }

    public void calculateFinal(){
        finalAmount = (amount*discount + tax) - payment;
    }

    @Override
    public String toString() {
        return ("Your bill:\nAmount: $" + amount + "    Discount: $" + discount + "    Tax: $" + tax + "    Total: $" + finalAmount);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
