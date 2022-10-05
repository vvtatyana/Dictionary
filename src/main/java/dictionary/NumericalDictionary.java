package dictionary;

import pair.Couples;
import workingWithFile.ReadingFromFile;
import workingWithFile.WritingToFile;

public class NumericalDictionary extends AbstractDictionary {
    @Override
    void createDictionary() {
        String filePath = "./src/main/resources/numericalDictionary.txt";
        writingToFile = new WritingToFile(filePath);
        couples = new Couples("[0-9]{1,5}");
        couples.addAll(new ReadingFromFile(filePath).read());
    }
}