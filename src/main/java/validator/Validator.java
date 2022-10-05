package validator;

import pair.Pair;

import java.util.regex.Pattern;

public class Validator {
    Pattern pattern;

    public Validator(String regex){
        pattern = Pattern.compile(regex);
    }

    public Boolean validationWord(Pair pair) {
        return pattern.matcher(pair.getWord()).find();
    }

    public Boolean validationTranslation(Pair pair) {
        return pattern.matcher(pair.getTranslation()).find();
    }

    public Boolean validationPair(Pair pair) {
        return validationWord(pair) && validationTranslation(pair);
    }
}