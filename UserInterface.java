package sorting;

import java.io.PrintStream;
import java.util.*;

public class UserInterface {
    private final Scanner scanner;
    private final String dataType;
    private final String sortingType;
    private PrintStream outputStream;

    public UserInterface(Scanner scanner, String dataType, String sortingType) {
        this.scanner = scanner;
        this.dataType = dataType;
        this.sortingType = sortingType;
        this.outputStream = System.out;
    }

    public void setOutputStream(PrintStream outputStream) {
        this.outputStream = outputStream;
    }

    public void boot() {
        if (this.sortingType.equals("natural")) {
            switch (this.dataType) {
                case "long":
                    this.sortIntegers();
                    break;
                case "word":
                    this.sortWords();
                    break;
                case "line":
                    this.sortLines();
                    break;
            }
        }

        if (this.sortingType.equals("byCount")) {
            switch (this.dataType) {
                case "long":
                    this.sortIntegersByCount();
                    break;
                case "word":
                    this.sortWordsByCount();
                    break;
                case "line":
                    this.sortLinesByCount();
                    break;
            }
        }
    }

    private void sortIntegersByCount() {
        Map<Long, Integer> countMap = new HashMap<>();
        List<Long> numbers = new ArrayList<>();

        while (this.scanner.hasNext()) {
            String input = this.scanner.next();

            try {
                long number = Long.parseLong(input);
                numbers.add(number);
                countMap.put(number, countMap.getOrDefault(number, 0) + 1);
            } catch (NumberFormatException e) {
                this.outputStream.println("\"" + input + "\" is not a long. It will be skipped.");
            }
        }

        numbers.sort((a, b) -> {
            int countDiff = countMap.get(a) - countMap.get(b);
            return (countDiff != 0) ? countDiff : a.compareTo(b);
        });

        int totalCount = numbers.size();

        Set<Long> printedNumbers = new HashSet<>();

        this.outputStream.printf("Total numbers: %d.\n", totalCount);
        for (long number : numbers) {
            if (printedNumbers.contains(number)) {
                continue;
            }

            int count = countMap.get(number);
            double percentage = (count / (double) totalCount) * 100;
            this.outputStream.printf("%d: %d time(s), %.0f%%\n", number, count, percentage);
            printedNumbers.add(number);
        }
    }

    private void sortWordsByCount() {
        Map<String, Integer> countMap = new HashMap<>();
        List<String> words = new ArrayList<>();

        while (this.scanner.hasNextLine()) {
            String line = this.scanner.nextLine();
            String[] lineWords = line.split("\\s+");
            words.addAll(Arrays.asList(lineWords));
            for (String word : lineWords) {
                countMap.put(word, countMap.getOrDefault(word, 0) + 1);
            }
        }

        words.sort((a, b) -> {
            int countDiff = countMap.get(a) - countMap.get(b);
            return (countDiff != 0) ? countDiff : a.compareTo(b);
        });

        int totalCount = words.size();

        Set<String> printedWords = new HashSet<>();

        this.outputStream.printf("Total words: %d.\n", totalCount);
        for (String word : words) {
            if (printedWords.contains(word)) {
                continue;
            }

            int count = countMap.get(word);
            double percentage = (count / (double) totalCount) * 100;
            this.outputStream.printf("%s: %d time(s), %.0f%%\n", word, count, percentage);
            printedWords.add(word);
        }
    }

    private void sortLinesByCount() {
        Map<String, Integer> countMap = new HashMap<>();
        List<String> lines = new ArrayList<>();

        while (this.scanner.hasNextLine()) {
            String line = this.scanner.nextLine();
            lines.add(line);
            countMap.put(line, countMap.getOrDefault(line, 0) + 1);
        }

        lines.sort((a, b) -> {
            int countDiff = countMap.get(a) - countMap.get(b);
            return (countDiff != 0) ? countDiff : a.compareTo(b);
        });

        int totalCount = lines.size();

        Set<String> printedLines = new HashSet<>();

        this.outputStream.printf("Total lines: %d.\n", totalCount);
        for (String line : lines) {
            if (printedLines.contains(line)) {
                continue;
            }
            int count = countMap.get(line);
            double percentage = (count / (double) totalCount) * 100;
            this.outputStream.printf("%s: %d time(s), %.0f%%\n", line, count, percentage);
            printedLines.add(line);
        }
    }

    private void sortIntegers() {
        int count = 0;

        List<Long> numbers = new ArrayList<>();
        while (this.scanner.hasNext()) {
            String input = this.scanner.next();

            try {
                long number = Long.parseLong(input);
                count++;
                numbers.add(number);
            } catch (NumberFormatException e) {
                this.outputStream.println("\"" + input + "\" is not a long. It will be skipped.");
            }
        }

        numbers.sort(Long::compareTo);

        this.outputStream.printf("Total numbers: %d.\n", count);
        this.outputStream.print("Sorted data: ");
        for (Long number : numbers) {
            if (number.equals(numbers.get(numbers.size() - 1))) {
                this.outputStream.print(number);
            } else {
                this.outputStream.print(number + " ");
            }
        }
    }

    private void sortWords() {
        int count = 0;
        List<String> words = new ArrayList<>();

        while (this.scanner.hasNextLine()) {
            String line = this.scanner.nextLine();
            String[] lineWords = line.split("\\s+");
            words.addAll(Arrays.asList(lineWords));
            count += lineWords.length;
        }

        words.sort(String::compareTo);

        this.outputStream.printf("Total words: %d.\n", count);
        this.outputStream.print("Sorted data: ");
        for (String word : words) {
            if (word.equals(words.get(words.size() - 1))) {
                this.outputStream.print(word);
            } else {
                this.outputStream.print(word + " ");
            }
        }
    }

    private void sortLines() {
        List<String> lines = new ArrayList<>();

        while (this.scanner.hasNextLine()) {
            String line = this.scanner.nextLine();
            lines.add(line);
        }

        lines.sort(String::compareTo);

        this.outputStream.printf("Total lines: %d.\n", lines.size());
        this.outputStream.println("Sorted data:");
        for (String line : lines) {
            this.outputStream.println(line);
        }
    }
}
