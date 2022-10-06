package console;

import dictionary.DictionaryBuild;
import exception.WordValidationError;

import java.io.InputStreamReader;
import java.util.Scanner;


public class Console {
    static Scanner scanner = new Scanner(new InputStreamReader(System.in));
    static DictionaryBuild dictionaryData = null;
    static String NOT_FOUND = "Нет такой команды";

    public void start(){
        while (true) {
            boolean exitFlag = choosingDictionary();
            ActionWithDictionary action = new ActionWithDictionary(dictionaryData);
            while (!exitFlag) {
                exitFlag = workingWithDictionary(action);
            }
        }
    }

    private static Boolean choosingDictionary() {
        System.out.println("""

                Выберите словарь:
                1. Латинский словарь
                2. Циферный словарь
                3. Выход
                """);

        switch (scanner.nextInt()) {
            case 1 -> dictionaryData = DictionaryBuild.ALPHABETIC;
            case 2 -> dictionaryData = DictionaryBuild.NUMERICAL;
            case 3 -> System.exit(0);
            default -> {
                System.out.println(NOT_FOUND);
                return true;
            }
        }
        return false;
    }

    private static Boolean workingWithDictionary(ActionWithDictionary action){
        System.out.println("""

                Выберите:
                1. Чтение списка пар из словаря
                2. Удаление пары из словаря
                3. Поиск записи по ключу
                4. Добавление записи в словарь
                5. Сменить словарь
                6. Выход
                """);

        try {
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
                    String read = action.readPair(key);
                    if (read != null) System.out.println(action.readPair(key));
                    break;
                case 4:
                    System.out.println("Введите пару слов через пробел:");
                    action.writePair(scanner.next(), scanner.next());
                    break;
                case 5:
                    return true;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.println(NOT_FOUND);
                    break;
            }
        } catch (NullPointerException npe) {
            System.out.println("Нет такого слова в словаре!");
        } catch (WordValidationError wve){
            System.out.println(wve.getMessage());
        }
        return false;
    }
}
