package printers.printers;

import printers.model.PrintCommand;
import printers.model.PrinterType;
import printers.support.Utils;

public class SimplePrinter extends BasePrinter implements Printer {

    public SimplePrinter(PrinterType printerType, int costPerSecond) {
        super(printerType, costPerSecond);
    }

    @Override
    public boolean isValidPrint(PrintCommand printCommand) {
        return !Utils.isLargePrint(printCommand) && !Utils.isColoredPrint(printCommand) && !Utils.isWoodenPrint(printCommand);
    }
}
