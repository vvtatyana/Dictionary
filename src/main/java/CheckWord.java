public class CheckWord {
    Boolean checkWord(String word, int size, String filter) {
        return checkingSize(word, size) && checkingContent(word, filter);
    }

    private Boolean checkingSize(String word, int size) {
        return word.length() == size;
    }

    private Boolean checkingContent(String word, String filter) {
        return word.matches(filter);
    }
}