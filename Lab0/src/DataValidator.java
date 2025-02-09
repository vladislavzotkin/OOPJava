import exceptions.*;

public class DataValidator {
    public static void validateArgs(String[] args) throws DataValidationException {
        if (args == null || args.length != 2) {
            throw new DataValidationException(
                    "Неверное количество аргументов. " +
                            "Использование: <имя входного файла> <имя выходного файла>"
            );
        }
    }
}