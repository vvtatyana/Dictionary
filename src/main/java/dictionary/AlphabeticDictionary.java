package dictionary;

import pair.Couples;
import workingWithFile.ReadingFromFile;
import workingWithFile.WritingToFile;

public class AlphabeticDictionary extends AbstractDictionary {
    @Override
    void createDictionary() {
        String filePath = "./src/main/resources/alphabeticDictionary.txt";
        writingToFile = new WritingToFile(filePath);
        couples = new Couples("^[a-zA-Z]{1,4}");
        couples.addAll(new ReadingFromFile(filePath).read());
    }
}
