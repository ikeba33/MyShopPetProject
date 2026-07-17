import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Collections;

public class Cart {
    private final ArrayList<Product> items = new ArrayList<>();


    // Метод добавления товара в корзину
    public void add(Product product) {
        items.add(product); // ArrayList сам добавит элемент в конец и расширится
        IO.println("Товар " + product.getName() + " добавлен в корзину!");
    }

    public void checkout() {
        if (items.isEmpty()) {
            System.out.println("Ошибка: Нельзя оформить заказ, корзина пуста!");
            return;
        }

        // Генерируем случайный 6-значный номер заказа
        int orderNumber = 100000 + new Random().nextInt(900000);

        // Получаем текущую дату и время
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = now.format(formatter);

        // Печатаем красивый чек
        System.out.println("\n========================================");
        System.out.println("             ООО \"МОЙ МАГАЗИН\"          ");
        System.out.println("               КАССОВЫЙ ЧЕК             ");
        System.out.println("========================================");
        System.out.printf(" Заказ №:      %d\n", orderNumber);
        System.out.printf(" Дата и время: %s\n", formattedDate);
        System.out.println("----------------------------------------");
        System.out.println(" Товары в чеке:");

        double finalTotal = 0;
        int position = 1;
        for (Product product : items) {
            double price = product.getPrice();
            String discountInfo = "";

            if (product instanceof Discountable discountableProduct) {
                price = discountableProduct.getDiscountPrice();
                discountInfo = " (Скидка 15%)";
            }

            System.out.printf(" %d. %-18s %8.2f $%s\n", position++, product.getName(), price, discountInfo);
            finalTotal += price;
        }

        System.out.println("----------------------------------------");
        System.out.printf(" ИТОГО К ОПЛАТЕ:          %8.2f $\n", finalTotal);
        System.out.println("========================================");
        System.out.println("         Спасибо за покупку!            ");
        System.out.println("========================================\n");

        // Очищаем корзину после успешной покупки
        clearCart();
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

    public void sortProductByPrice() {
        if (items.isEmpty()) {
            System.out.println("Корзина пуста, сортировать нечего.");
            return;
        }
        // Магия Java: Collections.sort автоматически использует метод compareTo,
        // который мы только что написали в классе Product!
        Collections.sort(items);
        System.out.println("Товары в корзине успешно отсортированы по цене (по возрастанию)!");
    }
}



