public class Main {
    public static void main(String[] args) {
        Product[] myShopProducts = new Product[4];
        myShopProducts[0] = new Food("Apples", 10, 1.5);
        myShopProducts[1] = new Electronics("iphone 15", 10, 999.0, 24);
        myShopProducts[2] = new Food("Pear", 5, 2.5);
        myShopProducts[3] = new Electronics("Samsung s24", 7, 666.6, 48);

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
            IO.println("4. Выйти");
            IO.println("5. очистить корзину");

            // Считываем выбор пользователя
            String choise = IO.readln("Выберите действие (1-4): ");
            switch (choise) {
                case "1" -> {
                    IO.println("\n--- ВИТРИНА ТОВАРОВ ---");
                    for (int i = 0; i < myShopProducts.length; i++) {
                        IO.println((i + 1) + ". [" + myShopProducts[i].getCategory() + "] ");
                        myShopProducts[i].printInfo();
                    }
                }
                case "2" -> {
                    String inputIndex = IO.readln("Введите номер товара для добавления в корзину: ");
                    // Переводим строку в число и смещаем на 1 назад (так как индексы с 0)
                    try {

                        int productIndex = Integer.parseInt(inputIndex) - 1;

                        if (productIndex >= 0 && productIndex < myShopProducts.length) {
                            Product selectedProduct = myShopProducts[productIndex];

                            // Проверяем наличие на складе
                            // Внимание: так как stock у нас private, нам нужен геттер! (Добавим его ниже)
                            if (selectedProduct.getStock() > 0) {
                                // Уменьшаем остаток товара
                                selectedProduct.setStock(selectedProduct.getStock() - 1);
                                myCart.add(selectedProduct); // Кладем в корзину!
                            } else {
                                IO.println("Извините, этот товар закончился!");
                            }
                        } else {
                            IO.println("Неверный номер товара!");
                        }
                    } catch (NumberFormatException e) {
                        IO.println("Ошибка ввода! Пожалуйста, вводите только целые числа.");
                    }
                }
                case "3" -> {
                    myCart.printCartInfo();
                }
                case "4" -> {
                    IO.println("Спасибо за визит! Всего доброго.");
                    isRunning = false;
                }
                case "5" -> {
                    myCart.clearCart();
                }
                default -> IO.println("Неизвестная команда.");
            }
        }


    }


//        for (int i = 0; i < myShopProducts.length; i++) {
//            System.out.println();
//            System.out.println("Категория: " + myShopProducts[i].getCategory());
//            myShopProducts[i].printInfo();
//            System.out.println();
//        }

}
