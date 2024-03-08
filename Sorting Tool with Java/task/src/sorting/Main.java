package sorting;

import sorting.Input.ConsoleInput;

public class Main {
    public static void main(final String[] args) {
        ConsoleInput<?> consoleInput = ConsoleInput.getInstance(args);
        consoleInput.printStats();
    }
}
