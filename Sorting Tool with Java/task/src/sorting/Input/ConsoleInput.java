package sorting.Input;

import java.util.*;

public class ConsoleInput<T extends Comparable<T>> {
    private final DataType dataType;
    private final ArrayList<T> data;

    private ConsoleInput(DataType dataType, ArrayList<T> data) {
        this.dataType = dataType;
        this.data = data;
    }

    public static ConsoleInput<?> getInstance(String[] args) {
        try(Scanner scanner = new Scanner(System.in)) {
            if (args.length > 0 && args[0].equals("-dataType")) {
                if (args[1].equals("long")) {
                    return ConsoleInput.getInputLONG(scanner);
                }
                if (args[1].equals("line")) {
                    return ConsoleInput.getInputLINE(scanner);
                }
            }
            return ConsoleInput.getInputWORD(scanner);
        }
    }

    private static ConsoleInput<Long> getInputLONG(Scanner scanner) {
        ArrayList<Long> input = new ArrayList<>();
        while (scanner.hasNext()) {
            input.add(scanner.nextLong());
        }
        return new ConsoleInput<>(DataType.LONG, input);
    }
    private static ConsoleInput<String> getInputLINE(Scanner scanner) {
        ArrayList<String> input = new ArrayList<>();
        while (scanner.hasNext()) {
            input.add(scanner.nextLine());
        }
        return new ConsoleInput<>(DataType.LINE, input);
    }
    private static ConsoleInput<String> getInputWORD(Scanner scanner) {
        ArrayList<String> input = new ArrayList<>();
        while (scanner.hasNext()) {
            input.add(scanner.next());
        }
        return new ConsoleInput<>(DataType.WORD, input);
    }

    private int getNumberOfElements() {
        return data.size();
    }

    private String getMax() {
        return Collections.max(data, new CustomComparator()).toString();
    }

    private int getFrequencyOfMax() {
        return Collections.frequency(data, Collections.max(data, new CustomComparator()));
    }

    private int getFrequencyPercentage() {
        return (Collections
                .frequency(data, Collections
                                .max(data, new CustomComparator()))
                * 100
                / data.size());
    }

    public void printStats() {
        switch (dataType) {
            case LONG -> printLONG();
            case LINE -> printLINE();
            case WORD -> printWORD();
        }
    }

    private void printLONG() {
        System.out.printf("Total numbers: %d.\n", getNumberOfElements());
        System.out.printf("The greatest number: %s (%d time(s), %d%%).\n", getMax(), getFrequencyOfMax(), getFrequencyPercentage());
    }

    private void printLINE() {
        System.out.printf("Total lines: %d.\n", getNumberOfElements());
        System.out.printf("The longest line:\n%s\n(%d time(s), %d%%).\n", getMax(), getFrequencyOfMax(), getFrequencyPercentage());
    }

    private void printWORD() {
        System.out.printf("Total words: %d.\n", getNumberOfElements());
        System.out.printf("The longest word: %s (%d time(s), %d%%).\n", getMax(), getFrequencyOfMax(), getFrequencyPercentage());
    }

    // from ChatGPT, should've done different classes for Long/String
    private static class CustomComparator implements Comparator<Object> {
        @Override
        public int compare(Object o1, Object o2) {
            if (o1 instanceof String s1 && o2 instanceof String s2) {
                int lengthComparison = Integer.compare(s1.length(), s2.length());
                if (lengthComparison != 0) {
                    return lengthComparison;
                }
                return s1.compareTo(s2);
            } else if (o1 instanceof Long l1 && o2 instanceof Long l2) {
                return Long.compare(l1, l2);
            } else {
                // Handle comparison between different types if needed
                return 0;
            }
        }
    }
}
