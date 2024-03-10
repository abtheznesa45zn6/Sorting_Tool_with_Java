package sorting.DataObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class DataString extends AbstractDataObject{
    private ArrayList<String> data = new ArrayList<>();
    DataString(DataType dataType, SortingType sortingType) {
        super(dataType, sortingType);
    }

    @Override
    protected int getNumberOfElements() {
        return data.size();
    }

    @Override
    protected void printTotal() {
        if (dataType.equals(DataType.WORD)) {
            System.out.printf("Total words: %d.\n", getNumberOfElements());
        } else {
            System.out.printf("Total lines: %d.\n", getNumberOfElements());
        }
    }

    @Override
    protected void printNatural() {
        data.sort(Comparator.naturalOrder());
        StringBuilder output = new StringBuilder();
        if (dataType.equals(DataType.WORD)) {
            for (String s : data) {
                output.append(" ").append(s);
            }
        } else {
            for (String s : data) {
                output.append(s).append("\n");
            }
        }
        System.out.printf("Sorted data: %s\n", output);
    }

    @Override
    protected void printByCount() {
        data.stream()
                .distinct()
                .sorted(new CustomComparator())
                .map(l -> l + ": " + getFrequency(l) + " time(s), " + getFrequencyPercentage(l) + "%")
                .forEach(System.out::println);
    }

    protected int getFrequency(String s) {
        return Collections.frequency(data, s);
    }

    protected int getFrequencyPercentage(String s) {
        return getFrequency(s) * 100 / data.size();
    }

    @Override
    protected void readInputFrom(Scanner scanner) {
        ArrayList<String> input = new ArrayList<>();
        if (dataType.equals(DataType.WORD)) {
            while (scanner.hasNext()) {
                input.add(scanner.next());
            }
        } else {
            while (scanner.hasNext()) {
                input.add(scanner.nextLine());
            }
        }
        data = input;
    }

    private class CustomComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            int freqCompare = Integer.compare(getFrequency(o1), getFrequency(o2));
            if (freqCompare != 0) {
                return freqCompare;
            } else {
                return String.CASE_INSENSITIVE_ORDER.compare(o1, o2);
            }
        }
    }
}
