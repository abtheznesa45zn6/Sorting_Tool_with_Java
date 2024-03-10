package sorting;

import sorting.Input.ConsoleIO;
import sorting.Input.DataType;

import java.util.Arrays;

public class Main {
    public static void main(final String[] args) {
        ConsoleIO<?> consoleIO = ConsoleIO.getInstance(getDataTypeArgument(args), getSortedArgument(args));
        consoleIO.printStats();
    }

    private static DataType getDataTypeArgument(String[] args) {
        if (args.length > 0 && args[0].equals("-dataType")) {
            if (args[1].equals("long")) {
                return DataType.LONG;
            }
            if (args[1].equals("line")) {
                return DataType.LINE;
            }
        }
        return DataType.WORD;
    }
    private static boolean getSortedArgument(String[] args) {
        return Arrays.asList(args).contains("-sortIntegers");
    }
}
