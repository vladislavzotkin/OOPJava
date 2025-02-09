import java.io.*;
import java.util.List;
import exceptions.*;

public class CSVFileWriter {
    private final String outputFilePath;

    public CSVFileWriter(String outputFilePath) {
        this.outputFilePath = outputFilePath;
    }

    public void writeRecords(List<WordRecord> records) throws FileProcessingException {
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(outputFilePath)))
        ) {

            writer.write("Word,Count,Frequency (%)");
            writer.newLine();

            for (WordRecord record : records) {
                writer.write(String.format("%s,%d,%.2f",
                        record.word(),
                        record.count(),
                        record.frequencyPercent()));
                writer.newLine();
            }

        } catch (IOException e) {
            throw new FileProcessingException("Ошибка при записи в файл: " + outputFilePath, e);
        }
    }
}