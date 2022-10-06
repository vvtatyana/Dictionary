package console;

import dictionary.DictionaryData;
import exception.WordValidationError;

import java.io.InputStreamReader;
import java.util.Scanner;


public class Console {
    static Scanner scanner = new Scanner(new InputStreamReader(System.in));
    static DictionaryData dictionaryData = null;
    static String NOT_FOUND = "Нет такой команды";

    public void start() {
        while (true) {
            boolean exitFlag = choosingDictionary();
            ActionWithDictionary action = new ActionWithDictionary(dictionaryData);
            while (!exitFlag) {
                exitFlag = workingWithDictionary(action);
            }
        }
    }

    private static Boolean choosingDictionary() {
        System.out.println("\n Выберите словарь:" +
                "1. Латинский словарь" +
                "2. Циферный словарь" +
                "3. Выход\n");

        switch (scanner.nextInt()) {
            case 1:
                dictionaryData = DictionaryData.ALPHABETIC;
                break;
            case 2:
                dictionaryData = DictionaryData.NUMERICAL;
                break;
            case 3:
                System.exit(0);
            default: {
                System.out.println(NOT_FOUND);
                return true;
            }
        }
        return false;
    }

    private static Boolean workingWithDictionary(ActionWithDictionary action) {
        System.out.println("\nВыберите:" +
                "1. Чтение списка пар из словаря" +
                "2. Удаление пары из словаря" +
                "3. Поиск записи по ключу" +
                "4. Добавление записи в словарь" +
                "5. Сменить словарь" +
                "6. Выход\n");

        try {
            switch (scanner.nextInt()) {
                case 1:
                    String readDictionary = action.readAllDictionary();
                    if (!readDictionary.isEmpty()) System.out.println(action.readAllDictionary());
                    else System.out.println("Словарь пустой");
                    break;
                case 2:
                    System.out.println("Введите ключ пары для удаления:");
                    String key = scanner.next();
                    if (!action.deletePair(key))
                        System.out.println("Не удалось удалить пару слов из словаря");
                    break;
                case 3:
                    System.out.println("Введите ключ для поиска:");
                    key = scanner.next();
                    String readPair = action.readPair(key);
                    if (!readPair.isEmpty()) System.out.println(action.readPair(key));
                    else System.out.println("В словаре пары с таким ключом");
                    break;
                case 4:
                    System.out.println("Введите пару слов через пробел:");
                    if (!action.writePair(scanner.next(), scanner.next()))
                        System.out.println("Не удалось записать в словарь");
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
        } catch (WordValidationError wve) {
            System.out.println(wve.getMessage());
        }
        return false;
    }
}
