package exception;

public class WordValidationError extends RuntimeException{
    public  WordValidationError(){
        super("Не верные символы в слове или не верное количество символов");
    }
}
