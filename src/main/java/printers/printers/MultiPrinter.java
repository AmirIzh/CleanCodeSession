package printers.printers;

import printers.model.PrintCommand;
import printers.model.PrinterType;

public class MultiPrinter extends BasePrinter implements Printer {

    public MultiPrinter(PrinterType printerType, int costPerSecond) {
        super(printerType, costPerSecond, false);
    }

    @Override
    public String isValidPrint(PrintCommand printCommand) {
        return null;
    }
}
