import java.io.*;
import java.util.ArrayList;
import java.util.Locale;

public class Database {
    private static final String FILE_NAME = "products.txt";

    // Метод для загрузки товаров из файла
    public static ArrayList<Product> loadProducts() {
        ArrayList<Product> products = new ArrayList<>();
        File file = new File(FILE_NAME); // это просто абстрактный путь (ссылка) к файлу или папке в твоей файловой системе.

        // Если файла еще нет, создаем его с дефолтными товарами
        if (!file.exists()) {
            createDefaultFile();
        }

        //FileReader открывает файл на чтение.
        //BufferedReader оборачивает его, чтобы читать файл не по одному символу (что очень медленно),
        // а сразу пачками. Также он дает нам супер-удобный метод readLine() для чтения файла построчно.

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                // Разбиваем строку по точке с запятой
                String[] parts = line.split(";");
                if (parts.length < 4) continue;

                String category = parts[0];
                String name = parts[1];
                double price = Double.parseDouble(parts[2]);
                int quantity = Integer.parseInt(parts[3]);

                // В зависимости от категории создаем нужный объект
                if (category.equalsIgnoreCase("Electronics")) {
                    products.add(new Electronics(name, price, quantity));
                } else if (category.equalsIgnoreCase("Food")) {
                    products.add(new Food(name, price, quantity));
                }
            }
        } catch (IOException | NumberFormatException e) {
            IO.println("Error reading file" + e.getMessage());
        }
        return products;
    }

    // Метод для сохранения текущего состояния товаров в файл
    public static void saveProducts(ArrayList<Product> products) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            // Записываем в формате: Категория;Название;Цена;Количество
            for (Product product : products) {
                writer.write(String.format(Locale.US,"%s;%s;%.2f;%d\n", product.getCategory().equalsIgnoreCase("Электроника") ? "Electronics" : "Food",
                        product.getName(),
                        product.getPrice(),
                        product.getQuantity()));

            }
        }catch (IOException e) {
            IO.println("Не удалось создать дефолтный файл базы данных." + e.getMessage());
        }
    }

    private static void createDefaultFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write("Electronics;iPhone 15;999.00;3\n");
            writer.write("Food;Pear;2.50;50\n");
            writer.write("Food;Apples;1.50;100\n");
            writer.write("Electronics;Samsung S24;899.00;2\n");
        } catch (IOException e) {
            System.out.println("Не удалось создать дефолтный файл базы данных.");
        }

    }


}
