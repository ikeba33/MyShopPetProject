public abstract class Product implements Comparable<Product> {
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
    public int compareTo(Product other){
        // Сравниваем цены двух товаров.
        // Double.compare возвращает:
        // отрицательное число, если наша цена меньше цены 'other'
        // положительное число, если наша цена больше
        // 0, если цены равны
        return Double.compare(this.getPrice(), other.getPrice());
    }

}
