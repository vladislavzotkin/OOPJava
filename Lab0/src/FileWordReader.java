import java.io.*;
import java.util.ArrayList;
import java.util.List;
import exceptions.*;

public class FileWordReader {
    private final String inputFilePath;

    public FileWordReader(String inputFilePath) {
        this.inputFilePath = inputFilePath;
    }

    public List<String> readAllLines() throws FileProcessingException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(inputFilePath)))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            throw new FileProcessingException("Ошибка при чтении файла: " + inputFilePath, e);
        }
        return lines;
    }
}