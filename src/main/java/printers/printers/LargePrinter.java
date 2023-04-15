package printers.printers;

import printers.model.PrintCommand;
import printers.model.PrinterType;
import printers.support.Utils;

public class LargePrinter extends BasePrinter implements Printer {

    public LargePrinter(PrinterType printerType, int costPerSecond) {
        super(printerType, costPerSecond);
    }

    @Override
    public boolean isValidPrint(PrintCommand printCommand) {
        return !Utils.isColoredPrint(printCommand) && !Utils.isWoodenPrint(printCommand);
    }
}
