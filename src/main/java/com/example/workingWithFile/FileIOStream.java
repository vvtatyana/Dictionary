package com.example.workingWithFile;

import com.example.pair.Pair;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileIOStream {
    private String filePath;

    public void setFilePath(String filePath) {
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

    public void write(String dictionary) {
        try (OutputStream os = Files.newOutputStream(Paths.get(filePath))) {
            os.write(dictionary.getBytes(), 0, dictionary.length());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
