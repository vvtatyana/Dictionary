import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(new InputStreamReader(System.in));
    static Map<String, String> dictionary = new HashMap<>();
    static DictionaryData dictionaryData = DictionaryData.LETTER;

    private static void readFile() {
        try {
            dictionary.clear();
            File file = new File(dictionaryData.getPath());
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                String[] words = line.split(":");
                dictionary.put(words[0], words[1]);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void delete() {
        System.out.println("Введите ключ пары для удаления:");
        String key = scanner.next();
        dictionary.remove(key);
        writeToFile();
    }

    private static void writeToFile() {
        StringBuilder data = new StringBuilder();
        for (String keySet : dictionary.keySet()) {
            data.append(keySet).append(":").append(dictionary.get(keySet)).append("\n");
        }
        OutputStream os = null;
        try {
            os = Files.newOutputStream(Paths.get(dictionaryData.getPath()));
            os.write(data.toString().getBytes(), 0, data.length());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert os != null;
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void write() {
        System.out.println("Введите пару слов через пробел:");
        String key =  scanner.next();
        String value = scanner.next();
        if (key.length() <= dictionaryData.getSize()) {
            boolean math;
            if (dictionaryData == DictionaryData.LETTER) {
                math = key.matches("^[a-zA-Z]+$");
            } else {
                math = key.matches("\\d+");
            }
            if (math) {
                dictionary.put(key, value);
                writeToFile();
            } else
                System.out.println("Не верные символы в слове");

        } else
            System.out.println("Не верное количество символов в слове");
    }

    private static String searchKey(){
        System.out.println("Введите ключ для поиска:");
        return scanner.next();
    }

    public static void main(String[] args) {
        while (true) {
            System.out.print("\nВыберите словарь:" +
                    "\n1. Латинский словарь" +
                    "\n2. Циферный словарь" +
                    "\n3. Выход\n\n");

            boolean exitFlag = false;

            int in = scanner.nextInt();

            switch (in) {
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

            while (!exitFlag) {
                readFile();
                System.out.print("\nВыберите:" +
                        "\n1. Чтение списка пар из словаря" +
                        "\n2. Удаление пары из словаря" +
                        "\n3. Поиск записи по ключу" +
                        "\n4. Добавление записи в словарь" +
                        "\n5. Сменить словарь" +
                        "\n6. Выход\n\n");

                in = scanner.nextInt();

                switch (in) {
                    case 1:
                        System.out.println(dictionary);
                        break;
                    case 2:
                        delete();
                        break;
                    case 3:
                        String key = searchKey();
                        System.out.println(key + " - " + dictionary.get(key));
                        break;
                    case 4:
                        write();
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