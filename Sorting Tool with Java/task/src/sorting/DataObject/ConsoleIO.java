package sorting.DataObject;

import java.util.*;

public class ConsoleIO<T extends Comparable<T>> {
    private final DataType dataType;
    private final ArrayList<T> data;
    private final boolean sorted;

    ConsoleIO(DataType dataType, ArrayList<T> data, boolean sorted) {
        this.dataType = dataType;
        this.data = data;
        this.sorted = sorted;
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
        if (sorted){
            System.out.printf("Sorted data:%s\n", getSortedDate());
        } else {
            System.out.printf("The greatest number: %s (%d time(s), %d%%).\n", getMax(), getFrequencyOfMax(), getFrequencyPercentage());
        }
    }

    private void printLINE() {
        System.out.printf("Total lines: %d.\n", getNumberOfElements());
        if (sorted){
            System.out.printf("Sorted data:%s\n", getSortedDate());
        } else {
            System.out.printf("The longest line:\n%s\n(%d time(s), %d%%).\n", getMax(), getFrequencyOfMax(), getFrequencyPercentage());
        }
    }

    private void printWORD() {
        System.out.printf("Total words: %d.\n", getNumberOfElements());
        if (sorted){
            System.out.printf("Sorted data:%s\n", getSortedDate());
        } else {
            System.out.printf("The longest word: %s (%d time(s), %d%%).\n", getMax(), getFrequencyOfMax(), getFrequencyPercentage());
        }
    }

    private String getSortedDate() {
        data.sort(new CustomComparatorForInteger());
        StringBuilder output = new StringBuilder();
        for (T s : data) {
            output.append(" ").append(s.toString());
        }
        return output.toString();
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

    private static class CustomComparatorForInteger implements Comparator<Object> {
        @Override
        public int compare(Object o1, Object o2) {
            if (o1 instanceof String s1 && o2 instanceof String s2) {
                return Long.compare(Long.parseLong(s1), Long.parseLong(s2));
            } else if (o1 instanceof Long l1 && o2 instanceof Long l2) {
                return Long.compare(l1, l2);
            } else {
                return 0;
            }
        }
    }
}
