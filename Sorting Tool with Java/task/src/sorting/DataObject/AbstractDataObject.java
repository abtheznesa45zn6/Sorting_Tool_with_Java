package sorting.DataObject;

import java.util.Scanner;

public abstract class AbstractDataObject {
    protected final DataType dataType;
    protected final SortingType sortingType;

    AbstractDataObject(DataType dataType, SortingType sortingType) {
        this.dataType = dataType;
        this.sortingType = sortingType;
    }

    protected abstract int getNumberOfElements();

    public void printStats(){
        printTotal();
        printSortedData();
    }

    protected abstract void printTotal();

    protected void printSortedData() {
        if (sortingType.equals(SortingType.NATURAL)){
            printNatural();
        } else {
            printByCount();
        }
    }

    protected abstract void printNatural();

    protected abstract void printByCount();

    public void readInput(){
        try(Scanner scanner = new Scanner(System.in)){
            readInputFrom(scanner);
        }
    }

    protected abstract void readInputFrom(Scanner scanner);

    public static class Builder {
        private DataType dataType = DataType.WORD;
        private SortingType sortingType = SortingType.NATURAL;

        public Builder setDataType(DataType dataType) {
            this.dataType = dataType;
            return this;
        }

        public Builder setSortingType(SortingType sortingType) {
            this.sortingType = sortingType;
            return this;
        }

        public AbstractDataObject build() {
            return switch (dataType) {
                case LONG -> new DataLong(dataType, sortingType);
                case LINE, WORD -> new DataString(dataType, sortingType);
            };
        }
    }
}