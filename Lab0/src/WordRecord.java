public record WordRecord(String word, int count, double frequencyPercent) {
    @Override
    public String toString() {
        return String.format("WordRecord{word='%s', count=%d, freq=%.2f%%}",
                word, count, frequencyPercent);
    }
}