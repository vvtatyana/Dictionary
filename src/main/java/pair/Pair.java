package pair;

public class Pair {
    private final String word;
    private final String translation;

    public Pair(String word, String translation) {
        this.word = word;
        this.translation = translation;
    }

    public String getWord() {
        return word;
    }

    public String getTranslation() {
        return translation;
    }

    @Override
    public String toString(){
        return word + " - " + translation;
    }
}
