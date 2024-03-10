package sorting;

import sorting.DataObject.AbstractDataObject;
import sorting.DataObject.DataType;
import sorting.DataObject.SortingType;

import java.util.Arrays;

public class Main {
    public static void main(final String[] args) {
        AbstractDataObject.Builder builder = new AbstractDataObject.Builder();
        configureBuilder(args, builder);
        AbstractDataObject data = builder.build();
        data.readInput();
        data.printStats();
    }

    private static void configureBuilder(String[] args, AbstractDataObject.Builder builder) {
        var arglist = Arrays.asList(args);
       if (arglist.contains("-dataType")){
           builder.setDataType(
                   getDataTypeArgument(
                           args[arglist.indexOf("-dataType")+1]));
       }
       if (arglist.contains("-sortingType")){
           builder.setSortingType(
                   getSortingTypeArgument(
                           args[arglist.indexOf("-sortingType")+1]));
       }
    }
    private static DataType getDataTypeArgument(String arg) {
        if (arg.equals("long")) {
            return DataType.LONG;
        }
        if (arg.equals("line")) {
            return DataType.LINE;
        }
        return DataType.WORD;
    }
    private static SortingType getSortingTypeArgument(String arg) {
        if (arg.equals("natural")) {
            return SortingType.NATURAL;
        }
        if (arg.equals("byCount")) {
            return SortingType.BY_COUNT;
        }
        return SortingType.NATURAL;
    }
}
