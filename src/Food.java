public class Food extends Product implements Discountable{


    public Food(String name, int stock, double price){
        super(name,stock,price);

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
