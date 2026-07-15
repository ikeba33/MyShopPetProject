import java.util.ArrayList;

public class Cart {
    private final ArrayList<Product> items = new ArrayList<>();


    // Метод добавления товара в корзину
    public void add(Product product) {
        items.add(product); // ArrayList сам добавит элемент в конец и расширится
        IO.println("Товар " + product.getName() + " добавлен в корзину!");
    }

    // Метод для вывода содержимого и подсчета суммы
    public void printCartInfo() {
        // Проверяем на пустоту через встроенный метод isEmpty()
        if (items.isEmpty()) {
            IO.println("\nВаша корзина пуста.");
            return;
        }

        IO.println("\n--- СОДЕРЖИМОЕ КОРЗИНЫ (со скидками) ---");
        double totalSum = 0;
        // Цикл for-each работает с ArrayList точно так же идеально, как и с массивами!
        int index = 1;
        for (Product p : items) {
            double finalPrice = p.getPrice();
            String discountMarker = "";

            // Проверяем: реализует ли данный конкретный продукт интерфейс Discountable?
            if (p instanceof Discountable discountableProduct) {
                // Если да - приводим его к типу Discountable и берем цену со скидкой
                finalPrice = discountableProduct.getDiscountPrice();
                discountMarker = " (Скидка 15%!)";
            }

            // Выводим цену с округлением до 2 знаков для красоты
            System.out.printf("%d. %s | Цена: %.2f $%s\n", index, p.getName(), finalPrice, discountMarker);
            totalSum += finalPrice;
            index++;
        }

        IO.println("----------------------------------------");
        System.out.printf("Итого к оплате: %.2f $\n", totalSum);
    }

    public void clearCart() {
        items.clear();
        IO.println("Корзина полностью очищена!");
    }
}

