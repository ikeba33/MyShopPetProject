public class Food extends Product implements Discountable{


    public Food(String name, double price, int quantity) {
        super(name,price,quantity);

    }

    @Override
    public String getCategory() {
        return "Продукты питания";
    }


    @Override
    public double getDiscountPrice() {
        return getPrice() * 0.85;
    }
}
