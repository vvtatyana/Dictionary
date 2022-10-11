package com.example.console;

import com.example.dictionary.DictionaryData;
import com.example.exception.WordValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStreamReader;
import java.util.Scanner;

@Component
public class Console {
    Scanner scanner = new Scanner(new InputStreamReader(System.in));
    DictionaryData dictionaryData;
    String NOT_FOUND = "Нет такой команды";

    final ActionWithDictionary action;

    @Autowired
    public Console(ActionWithDictionary action) {
        this.action = action;
    }

    public void start() {
        while (true) {
            boolean exitFlag = choosingDictionary();
            action.createDictionary(dictionaryData);
            while (!exitFlag) {
                exitFlag = workingWithDictionary(action);
            }
        }
    }

    private Boolean choosingDictionary() {
        System.out.println("\nВыберите словарь:" +
                "\n1. Латинский словарь" +
                "\n2. Циферный словарь" +
                "\n3. Выход\n");

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

    private Boolean workingWithDictionary(ActionWithDictionary action) {
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
                    System.out.println(action.deletePair(scanner.next()));
                    break;
                case 3:
                    System.out.println("Введите ключ для поиска:");
                    System.out.println(action.searchPair(scanner.next()));
                    break;
                case 4:
                    System.out.println("Введите пару слов через пробел:");
                    System.out.println(action.writePair(scanner.next(), scanner.next()));
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
