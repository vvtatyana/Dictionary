import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class WorkingWithFile {

    Map<String, String> read(String path) {
        Map<String, String> pairsWords = new HashMap<>();
        try {
            File file = new File(path);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                String[] words = line.split(Separator.DICTIONARY.getSeparator());
                pairsWords.put(words[0], words[1]);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pairsWords;
    }

    void write(String path, String data) {
        OutputStream os = null;
        try {
            os = Files.newOutputStream(Paths.get(path));
            os.write(data.getBytes(), 0, data.length());
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
}
