import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Product> myShopProducts = Database.loadProducts();
        // Создаем КОРЗИНУ для нашего покупателя
        Cart myCart = new Cart();

        IO.println("--- Добро пожаловать в ООП-Магазин! ---");

        // Флаг для управления циклом работы магазина
        boolean isRunning = true;

        // 2. Главный цикл программы
        while (isRunning) {
            IO.println("\nДоступные действия:");
            IO.println("1. Посмотреть витрину");
            IO.println("2. Добавить товар в корзину");
            IO.println("3. Посмотреть корзину (и сумму)");
            IO.println("4. Отсортировать товары в корзине по цене ");
            IO.println("5. Оформить заказ");
            IO.println("6. очистить корзину");
            IO.println("7. Выйти");

            // Считываем выбор пользователя
            String choise = IO.readln("Выберите действие (1-7): ");
            switch (choise) {
                case "1" -> {
                    IO.println("\n--- ВИТРИНА ТОВАРОВ ---");
                    for (int i = 0; i < myShopProducts.size(); i++) {
                        Product p = myShopProducts.get(i);
                        System.out.printf("%d. %s — %.2f $ (В наличии: %d шт.)\n",
                                (i + 1), p.getName(), p.getPrice(), p.getQuantity());
                    }
                }
                case "2" -> {
                    String inputIndex = IO.readln("Введите номер товара для добавления в корзину: ");
                    // Переводим строку в число и смещаем на 1 назад (так как индексы с 0)
                    try {

                        int productIndex = Integer.parseInt(inputIndex) - 1;

                        if (productIndex >= 0 && productIndex < myShopProducts.size()) {
                            Product selectedProduct = myShopProducts.get(productIndex);

                            // Проверяем наличие на складе
                            // Внимание: так как stock у нас private, нам нужен геттер! (Добавим его ниже)
                            if (selectedProduct.getQuantity() > 0) {
                                myCart.add(selectedProduct); // Кладем в корзину!
                                // Уменьшаем остаток товара
                                selectedProduct.setQuantity(selectedProduct.getQuantity() - 1);
                                Database.saveProducts(myShopProducts);
                                System.out.printf("Товар %s успешно добавлен в корзину! (Осталось на складе: %d)\n",
                                        selectedProduct.getName(), selectedProduct.getQuantity());
                            } else {
                                System.out.printf("Извините, товар %s закончился на складе!\n", selectedProduct.getName());
                            }
                        } else {
                            IO.println("Неверный номер товара!");
                        }
                    } catch (NumberFormatException e) {
                        IO.println("Ошибка ввода! Пожалуйста, вводите только целые числа.");
                    }
                }
                case "3" -> myCart.printCartInfo();
                case "4" -> myCart.sortProductByPrice();
                case "5" -> myCart.checkout();
                case "6" -> {
                    myCart.clearCart();
                    Database.saveProducts(myShopProducts);
                }
                case "7" -> {
                    Database.saveProducts(myShopProducts);
                    IO.println("Спасибо за визит! Всего доброго.");
                    isRunning = false;
                }
                default -> IO.println("Неизвестная команда.");
            }
        }


    }
}
