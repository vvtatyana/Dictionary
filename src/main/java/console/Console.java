package console;

import exception.WordValidationError;

import java.io.InputStreamReader;
import java.util.Scanner;


public class Console {
    static Scanner scanner = new Scanner(new InputStreamReader(System.in));
    static String dictionaryData;
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
        System.out.println("\nВыберите словарь:" +
                "\n1. Латинский словарь" +
                "\n2. Циферный словарь" +
                "\n3. Выход\n");

        switch (scanner.nextInt()) {
            case 1:
                dictionaryData = "alphabeticDictionary";
                break;
            case 2:
                dictionaryData = "numericalDictionary";
                break;
            case 3:
                System.exit(0);
                break;
            default: {
                System.out.println(NOT_FOUND);
                return true;
            }
        }
        return false;
    }

    private static Boolean workingWithDictionary(ActionWithDictionary action){
        System.out.println("\nВыберите:" +
                "\n1. Чтение списка пар из словаря" +
                "\n2. Удаление пары из словаря" +
                "\n3. Поиск записи по ключу" +
                "\n4. Добавление записи в словарь" +
                "\n5. Сменить словарь" +
                "\n6. Выход\n");

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
