import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        DictionaryData dictionaryData = DictionaryData.LETTER;
        while (true) {
            System.out.println("\nВыберите словарь:" +
                    "\n1. Латинский словарь" +
                    "\n2. Циферный словарь" +
                    "\n3. Выход\n");
            boolean exitFlag = false;

            switch (scanner.nextInt()) {
                case 1:
                    dictionaryData = DictionaryData.LETTER;
                    break;
                case 2:
                    dictionaryData = DictionaryData.NUMBER;
                    break;
                case 3:
                    System.exit(0);
                    break;
                default: {
                    System.out.println("Нет такого!");
                    exitFlag = true;
                    break;
                }
            }

            Action action = new Action(dictionaryData);
            while (!exitFlag) {
                System.out.println("\nВыберите:" +
                        "\n1. Чтение списка пар из словаря" +
                        "\n2. Удаление пары из словаря" +
                        "\n3. Поиск записи по ключу" +
                        "\n4. Добавление записи в словарь" +
                        "\n5. Сменить словарь" +
                        "\n6. Выход\n");

                switch (scanner.nextInt()) {
                    case 1:
                        System.out.println(action.readAllDictionary());
                        break;
                    case 2:
                        System.out.println("Введите ключ пары для удаления:");
                        String key = scanner.next();
                        action.deletePair(key);
                        break;
                    case 3:
                        System.out.println("Введите ключ для поиска:");
                        key = scanner.next();
                        action.readPair(key);
                        System.out.println(action.readPair(key));
                        break;
                    case 4:
                        System.out.println("Введите пару слов через пробел:");
                        action.writePair(scanner.next(), scanner.next());
                        break;
                    case 5:
                        exitFlag = true;
                        break;
                    case 6:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Нет такого!");
                        break;
                }
            }
        }
    }
}