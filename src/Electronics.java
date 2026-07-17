public class Electronics extends Product{

    private int warrantyMonths;

    public Electronics(String name, double price, int quantity) {
        super(name, price, quantity);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public double getPrice() {
        return super.getPrice();
    }

    @Override
    public void setPrice(double price) {
        super.setPrice(price);
    }

    @Override
    public void printInfo() {
        super.printInfo();
    }

    @Override
    public String getCategory() {
        return "Электроника";
    }

    public int getWarrantyMonths() {
        return warrantyMonths;
    }

    public void setWarrantyMonths(int warrantyMonths) {
        this.warrantyMonths = warrantyMonths;
    }
}
