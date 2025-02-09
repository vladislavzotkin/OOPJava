import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import exceptions.*;

/**
 * 1. Чтение всех строк
 * 2. Разбиение на слова
 * 3. Подсчёт
 * 4. Сортировка
 * 5. Запись результатов
 */
public class WordCounter {
    private final FileWordReader reader;
    private final CSVFileWriter writer;

    public WordCounter(FileWordReader reader, CSVFileWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public void analyze() throws FileProcessingException, WordCountException {
        List<String> lines = reader.readAllLines();

        if (lines.isEmpty()) {
            throw new WordCountException("Файл пустой или содержит недоступный текст.");
        }

        Map<String, Integer> frequencyMap = computeFrequencies(lines);

        if (frequencyMap.isEmpty()) {
            throw new WordCountException("Не удалось выделить ни одного слова из файла.");
        }

        int totalWords = frequencyMap.values().stream()
                .mapToInt(Integer::intValue)
                .sum();

        List<WordRecord> records = frequencyMap.entrySet()
                .stream()
                .map(entry -> {
                    String word = entry.getKey();
                    int count = entry.getValue();
                    double freq = 100.0 * count / totalWords; // процент
                    return new WordRecord(word, count, freq);
                })
                .sorted((r1, r2) -> Integer.compare(r2.count(), r1.count()))
                .collect(Collectors.toList());

        writer.writeRecords(records);
    }

    private Map<String, Integer> computeFrequencies(List<String> lines) {
        Map<String, Integer> freqMap = new HashMap<>();

        // (всё, что не буква или цифра, считается разделителем)
        Pattern delimiter = Pattern.compile("[^A-Za-z0-9]+");

        for (String line : lines) {
            String[] words = delimiter.split(line);

            for (String w : words) {
                if (!w.isEmpty()) {
                    String lowerCaseWord = w.toLowerCase(Locale.ROOT);
                    freqMap.merge(lowerCaseWord, 1, Integer::sum);
                }
            }
        }
        return freqMap;
    }
}