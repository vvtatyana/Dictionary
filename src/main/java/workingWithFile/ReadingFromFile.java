package workingWithFile;

import pair.Pair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadingFromFile {
    private final String filePath;

    public ReadingFromFile (String filePath){
        this.filePath = filePath;
    }

    public List<Pair> readFromBuffer(BufferedReader reader) throws IOException {
        List<Pair> dictionary = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] words = line.split(" - ");
            dictionary.add(new Pair(words[0], words[1]));
        }
        return dictionary;
    }

    public List<Pair> read() {
        List<Pair> dictionary = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            dictionary = readFromBuffer(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dictionary;
    }
}
