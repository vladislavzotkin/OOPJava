import exceptions.*;

public class Main {
    public static void main(String[] args) {
        try {
            DataValidator.validateArgs(args);

            String inputFilePath = args[0];
            String outputFilePath = args[1];

            FileWordReader reader = new FileWordReader(inputFilePath);
            CSVFileWriter writer = new CSVFileWriter(outputFilePath);

            WordCounter wordCounter = new WordCounter(reader, writer);
            wordCounter.analyze();

            System.out.println("Анализ завершён. Результат сохранён в файл: " + outputFilePath);
        } catch (DataValidationException | FileProcessingException | WordCountException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}