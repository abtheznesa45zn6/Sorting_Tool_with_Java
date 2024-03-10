package sorting.DataObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class DataLong extends AbstractDataObject{
    private final ArrayList<Long> data = new ArrayList<>();
    DataLong(DataType dataType, SortingType sortingType) {
        super(dataType, sortingType);
    }

    @Override
    protected int getNumberOfElements() {
        return data.size();
    }

    @Override
    protected void printTotal() {
        System.out.printf("Total numbers: %d.\n", getNumberOfElements());
    }

    @Override
    protected void printNatural() {
        data.sort(Comparator.naturalOrder());
        StringBuilder output = new StringBuilder();
        for (Long l : data) {
            output.append(" ").append(l.toString());
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

    protected int getFrequency(Long l) {
        return Collections.frequency(data, l);
    }

    protected int getFrequencyPercentage(Long l) {
        return getFrequency(l) * 100 / data.size();
    }

    @Override
    protected void readInputFrom(Scanner scanner) {
        while (scanner.hasNext()) {
            data.add(scanner.nextLong());
        }
    }
    private class CustomComparator implements Comparator<Long> {
        @Override
        public int compare(Long o1, Long o2) {
            int freqCompare = Integer.compare(getFrequency(o1), getFrequency(o2));
            if (freqCompare != 0) {
                return freqCompare;
            } else {
                return Long.compare(o1, o2);
            }
        }
    }
}