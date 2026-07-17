public abstract class Product implements Comparable<Product> {
    private String name;
    private double price;
    private int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
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



    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void printInfo() {
        if (quantity == 0) {
            IO.println("Товар: " + name + " | НЕТ В НАЛИЧИИ");
        } else {
            IO.println("Товар: " + name + " | Цена: " + price + " | Остаток: " + quantity + " шт.");
        }
    }

    public abstract String getCategory();

    public int compareTo(Product other) {
        // Сравниваем цены двух товаров.
        // Double.compare возвращает:
        // отрицательное число, если наша цена меньше цены 'other'
        // положительное число, если наша цена больше
        // 0, если цены равны
        return Double.compare(this.getPrice(), other.getPrice());
    }

}
