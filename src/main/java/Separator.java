public enum Separator {
    DICTIONARY(":"),
    OUTPUT(" - ");

    private final String separator;

    Separator(String separator) {
        this.separator = separator;
    }

    String getSeparator() {
        return separator;
    }
}
