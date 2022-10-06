package dictionary;

public enum DictionaryBuild {
    ALPHABETIC(new AlphabeticDictionary()),
    NUMERICAL(new NumericalDictionary());

    private final IDictionary dictionary;

    DictionaryBuild(IDictionary dictionary){
        this.dictionary = dictionary;
    }

    public IDictionary getDictionary() {
        return dictionary;
    }
}
