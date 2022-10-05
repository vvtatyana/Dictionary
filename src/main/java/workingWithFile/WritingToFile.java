package workingWithFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WritingToFile {
    private final String filePath;

    public WritingToFile (String filePath){
        this.filePath = filePath;
    }

    public void write(String dictionary) {
        try (OutputStream os = Files.newOutputStream(Paths.get(filePath))) {
            os.write(dictionary.getBytes(), 0, dictionary.length());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
