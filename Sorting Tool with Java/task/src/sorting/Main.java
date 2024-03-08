package sorting;

import java.util.*;

public class Main {
    public static void main(final String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Long> numbers = new ArrayList<>();

        while (scanner.hasNextLong()) {
            numbers.add(scanner.nextLong());
        }

        System.out.printf("Total numbers: %d.\n", numbers.size());
        System.out.printf("The greatest number: %d (%d time(s)).\n", getGreatestNumber(numbers), getGreatestNumberCount(numbers));
    }

    private static long getGreatestNumber(ArrayList<Long> numbers) {
        Optional<Long> max = numbers.stream().max(Comparator.naturalOrder());

        if (max.isPresent()) {
            return max.get();
        }
        return Integer.MIN_VALUE;
    }

    private static long getGreatestNumberCount(ArrayList<Long> numbers) {
        return numbers.stream().filter(l -> l.equals(getGreatestNumber(numbers))).count();
    }
}
