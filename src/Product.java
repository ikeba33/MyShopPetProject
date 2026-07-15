public abstract class Product {
    private String name;
    private int stock;
    private double price;

    public Product(String name, int stock, double price) {
        this.name = name;
        this.stock = stock;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void printInfo() {
        if (stock == 0) {
            IO.println("Товар: " + name + " | НЕТ В НАЛИЧИИ");
        } else {
            IO.println("Товар: " + name + " | Цена: " + price + " | Остаток: " + stock + " шт.");
        }
    }

    public abstract String getCategory();


}
